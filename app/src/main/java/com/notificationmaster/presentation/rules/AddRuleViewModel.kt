package com.notificationmaster.presentation.rules

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.provider.Telephony
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.Rule
import com.notificationmaster.domain.usecase.ManageRulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Yüklü uygulama bilgisi — App Picker için.
 */
data class AppInfo(
    val appName: String,
    val packageName: String,
    val icon: Drawable?
)

@HiltViewModel
class AddRuleViewModel @Inject constructor(
    private val manageRules: ManageRulesUseCase,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {

    /** Edit mode: ruleId varsa mevcut kuralı düzenliyoruz */
    val editRuleId: Long? = savedStateHandle.get<Long>("ruleId")
    val isEditMode: Boolean = editRuleId != null && editRuleId > 0

    var packageName by mutableStateOf("")
    var selectedAppName by mutableStateOf("")
    var filterType by mutableStateOf(FilterType.APP_BLOCK)
    var keyword by mutableStateOf("")
    var action by mutableStateOf(FilterAction.BLOCK)
    var timeStart by mutableStateOf("22:00")
    var timeEnd by mutableStateOf("07:00")
    var allowedContactsText by mutableStateOf("")

    var isSaved by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    /** Yüklü uygulamalar listesi */
    var installedApps by mutableStateOf<List<AppInfo>>(emptyList())
        private set

    var isAppsLoading by mutableStateOf(false)
        private set

    init {
        // Edit mode ise mevcut kuralı yükle
        if (isEditMode && editRuleId != null) {
            loadRule(editRuleId)
        }
        // Yüklü uygulamaları arka planda yükle
        loadInstalledApps()
    }

    private fun loadInstalledApps() {
        isAppsLoading = true
        viewModelScope.launch {
            val apps = withContext(Dispatchers.IO) {
                val pm = context.packageManager
                val appMap = mutableMapOf<String, AppInfo>()

                // 1. Launcher uygulamaları (ana menüde görünenler)
                val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
                    addCategory(Intent.CATEGORY_LAUNCHER)
                }
                val resolveInfos = pm.queryIntentActivities(mainIntent, PackageManager.MATCH_ALL)
                for (resolveInfo in resolveInfos) {
                    val ai = resolveInfo.activityInfo.applicationInfo
                    if (!appMap.containsKey(ai.packageName)) {
                        appMap[ai.packageName] = AppInfo(
                            appName = ai.loadLabel(pm).toString(),
                            packageName = ai.packageName,
                            icon = try { ai.loadIcon(pm) } catch (_: Exception) { null }
                        )
                    }
                }

                // 2. SMS/Mesajlaşma uygulamaları
                try {
                    val defaultSms = Telephony.Sms.getDefaultSmsPackage(context)
                    if (defaultSms != null && !appMap.containsKey(defaultSms)) {
                        val smsInfo = pm.getApplicationInfo(defaultSms, 0)
                        appMap[defaultSms] = AppInfo(
                            appName = pm.getApplicationLabel(smsInfo).toString(),
                            packageName = defaultSms,
                            icon = try { smsInfo.loadIcon(pm) } catch (_: Exception) { null }
                        )
                    }
                } catch (_: Exception) { }

                // 3. Bildirim gönderebilen diğer uygulamalar (yüklü tüm uygulamalar)
                val allPackages = pm.getInstalledApplications(0)
                for (ai in allPackages) {
                    // Sadece kullanıcı uygulamalarını ve bilinen sistem uygulamalarını dahil et
                    val isUserApp = (ai.flags and android.content.pm.ApplicationInfo.FLAG_SYSTEM) == 0
                    val isKnownMessaging = ai.packageName in setOf(
                        "com.google.android.apps.messaging",
                        "com.android.mms",
                        "com.android.messaging",
                        "com.samsung.android.messaging",
                        "com.android.providers.telephony"
                    )
                    if ((isUserApp || isKnownMessaging) && !appMap.containsKey(ai.packageName)) {
                        appMap[ai.packageName] = AppInfo(
                            appName = pm.getApplicationLabel(ai).toString(),
                            packageName = ai.packageName,
                            icon = try { ai.loadIcon(pm) } catch (_: Exception) { null }
                        )
                    }
                }

                appMap.values.sortedBy { it.appName.lowercase() }
            }
            installedApps = apps
            isAppsLoading = false
        }
    }

    /**
     * Uygulama seçildiğinde çağrılır.
     */
    fun selectApp(app: AppInfo) {
        packageName = app.packageName
        selectedAppName = app.appName
    }

    private fun loadRule(ruleId: Long) {
        isLoading = true
        viewModelScope.launch {
            val rule = manageRules.getRuleById(ruleId)
            if (rule != null) {
                packageName = rule.packageName
                filterType = rule.filterType
                keyword = rule.keyword ?: ""
                action = rule.action
                timeStart = rule.timeStart ?: "22:00"
                timeEnd = rule.timeEnd ?: "07:00"
                allowedContactsText = rule.allowedContacts.joinToString(", ")

                // Uygulama adını paket adından bul
                try {
                    val pm = context.packageManager
                    val appInfo = pm.getApplicationInfo(rule.packageName, 0)
                    selectedAppName = pm.getApplicationLabel(appInfo).toString()
                } catch (_: Exception) {
                    selectedAppName = rule.packageName.substringAfterLast('.')
                        .replaceFirstChar { it.uppercase() }
                }
            }
            isLoading = false
        }
    }

    fun saveRule() {
        if (packageName.isBlank()) return

        viewModelScope.launch {
            val contacts = allowedContactsText
                .split(",")
                .map { it.trim() }
                .filter { it.isNotBlank() }

            val rule = Rule(
                id = editRuleId ?: 0,
                packageName = packageName,
                filterType = filterType,
                keyword = keyword.ifBlank { null },
                action = if (filterType == FilterType.CONTACT_WHITELIST) FilterAction.BLOCK else action,
                timeStart = if (filterType == FilterType.TIME_BASED) timeStart else null,
                timeEnd = if (filterType == FilterType.TIME_BASED) timeEnd else null,
                allowedContacts = if (filterType == FilterType.CONTACT_WHITELIST) contacts else emptyList()
            )
            manageRules.addRule(rule)
            isSaved = true
        }
    }
}
