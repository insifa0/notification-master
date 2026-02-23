package com.notificationmaster.domain.usecase

import android.util.Log
import com.notificationmaster.domain.engine.FilterEngine
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterResult
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.repository.INotificationRepository
import com.notificationmaster.domain.repository.IRuleRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Gelen bildirimi işleyen UseCase.
 * NotificationListenerService tarafından her bildirimde çağrılır.
 *
 * Akış:
 * 1. Aktif kuralları veritabanından al
 * 2. FilterEngine ile bildirimi değerlendir
 * 3. Sonucu geçmişe kaydet (BLOCK veya SILENT ise)
 * 4. FilterResult döndür (servis buna göre cancel/snooze yapar)
 */
class ProcessNotificationUseCase @Inject constructor(
    private val ruleRepository: IRuleRepository,
    private val notificationRepository: INotificationRepository,
    private val filterEngine: FilterEngine
) {

    companion object {
        private const val TAG = "ProcessNotification"
    }

    suspend operator fun invoke(notification: NotificationInfo): FilterResult {
        // 1. Aktif kuralları al
        val rules = ruleRepository.getAllActiveRules().first()
        Log.d(TAG, "📋 Yüklenen kural sayısı: ${rules.size}")
        rules.forEachIndexed { i, rule ->
            Log.d(TAG, "   [$i] ${rule.filterType} — paket: ${rule.packageName} — keyword: ${rule.keyword} — contacts: ${rule.allowedContacts} — action: ${rule.action}")
        }

        // 2. Filtre motoruyla değerlendir
        val result = filterEngine.evaluate(notification, rules)
        Log.d(TAG, "🎯 Sonuç: ${result.action} — ${result.reason}")

        // 3. BLOCK veya SILENT ise geçmişe kaydet
        if (result.action != FilterAction.ALLOW) {
            notificationRepository.logNotification(
                packageName = notification.packageName,
                appName = notification.appName,
                title = notification.title,
                contentPreview = notification.content?.take(100), // İlk 100 karakter
                actionTaken = result.action,
                matchedRuleId = result.matchedRule?.id
            )
        }

        return result
    }
}
