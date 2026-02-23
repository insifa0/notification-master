package com.notificationmaster.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.usecase.ProcessNotificationUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

/**
 * Çekirdek Servis — Tüm bildirimleri dinler ve filtre motoruna gönderir.
 *
 * Sonsuz döngü koruması:
 * - Her bildirim key'i en az COOLDOWN_MS süre içinde yeniden işlenmez
 * - snoozeNotification() yerine cancelNotification() kullanılır
 *   (snooze sistemi re-post yaparak sonsuz döngüye sebep olur)
 */
@AndroidEntryPoint
class NotificationListener : NotificationListenerService() {

    companion object {
        private const val TAG = "NotificationListener"

        /** Aynı bildirim key'i için minimum bekleme süresi (ms) */
        private const val COOLDOWN_MS = 5_000L

        /** İşlenmiş key cache temizleme eşiği */
        private const val MAX_CACHE_SIZE = 200
    }

    @Inject
    lateinit var processNotification: ProcessNotificationUseCase

    @Inject
    lateinit var relayManager: NotificationRelayManager

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    /**
     * Son işlenen bildirim key'leri ve zaman damgaları.
     * Aynı key'in COOLDOWN_MS içinde tekrar işlenmesini engeller.
     */
    private val processedKeys = ConcurrentHashMap<String, Long>()

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d(TAG, "✅ NotificationListener bağlandı! Bildirimler dinleniyor...")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.w(TAG, "⚠️ NotificationListener bağlantısı kesildi!")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn ?: return

        // Kendi bildirimlerimizi filtre et
        if (sbn.packageName == packageName) return

        // Sürekli bildirimleri atla (müzik çalar, navigasyon vb.)
        if (sbn.isOngoing) return

        // ⚡ SONSUZ DÖNGÜ KORUMASI: Aynı key'i COOLDOWN içinde tekrar işleme
        val now = System.currentTimeMillis()
        val lastProcessed = processedKeys[sbn.key]
        if (lastProcessed != null && (now - lastProcessed) < COOLDOWN_MS) {
            Log.d(TAG, "⏭️ ATLANACAK (cooldown): ${sbn.key} — son işleme: ${now - lastProcessed}ms önce")
            return
        }
        processedKeys[sbn.key] = now

        // Cache çok büyürse eski kayıtları temizle
        if (processedKeys.size > MAX_CACHE_SIZE) {
            cleanupCache(now)
        }

        val notificationInfo = sbn.toNotificationInfo()

        Log.d(TAG, "📩 Bildirim geldi:")
        Log.d(TAG, "   📦 Paket: ${notificationInfo.packageName}")
        Log.d(TAG, "   📱 Uygulama: ${notificationInfo.appName}")
        Log.d(TAG, "   📝 Başlık: '${notificationInfo.title ?: "(boş)"}'")
        Log.d(TAG, "   💬 İçerik: '${notificationInfo.content ?: "(boş)"}'")

        scope.launch {
            try {
                val result = processNotification(notificationInfo)

                when (result.action) {
                    FilterAction.BLOCK -> {
                        cancelNotification(sbn.key)
                        Log.d(TAG, "🚫 ENGELLENDİ: '${notificationInfo.title}' — ${result.reason}")
                    }
                    FilterAction.SILENT -> {
                        // ⚠️ snoozeNotification() KULLANMA! Sonsuz döngüye sebep olur.
                        // Bunun yerine bildirimi tamamen iptal ediyoruz.
                        cancelNotification(sbn.key)
                        Log.d(TAG, "🔇 SESSİZE ALINDI (silindi): '${notificationInfo.title}' — ${result.reason}")
                    }
                    FilterAction.ALLOW -> {
                        Log.d(TAG, "✅ GEÇTİ: '${notificationInfo.title}'")
                    }
                    FilterAction.RELAY -> {
                        // Orijinal bildirim telefonda KALIR — sadece kopya oluştur
                        relayManager.relay(notificationInfo)
                        Log.d(TAG, "📤 RELAY: '${notificationInfo.title}' — ${result.reason}")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Bildirim işlenirken hata: ${e.message}", e)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        // İsteğe bağlı: kaldırılan bildirimi cache'den çıkar
        sbn?.key?.let { processedKeys.remove(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        processedKeys.clear()
    }

    /**
     * Eski cache kayıtlarını temizle (COOLDOWN süresini geçenler).
     */
    private fun cleanupCache(now: Long) {
        val expired = processedKeys.entries.filter { now - it.value > COOLDOWN_MS * 2 }
        expired.forEach { processedKeys.remove(it.key) }
        Log.d(TAG, "🧹 Cache temizlendi: ${expired.size} eski kayıt silindi")
    }

    /**
     * StatusBarNotification → NotificationInfo dönüşümü.
     *
     * Android 12+ "Sensitive notification content hidden" döndürebilir.
     * Bu durumda MessagingStyle messages dizisinden gerçek içeriğe ulaşırız.
     */
    private fun StatusBarNotification.toNotificationInfo(): NotificationInfo {
        val extras = notification.extras

        // Başlık: android.title → tickerText fallback
        var title = extras?.getCharSequence("android.title")?.toString()
            ?: notification.tickerText?.toString()

        // İçerik: android.text → android.bigText → android.subText
        var content = extras?.getCharSequence("android.text")?.toString()
            ?: extras?.getCharSequence("android.bigText")?.toString()
            ?: extras?.getCharSequence("android.subText")?.toString()

        // ⚠️ "Sensitive notification content hidden" koruması:
        // MessagingStyle mesaj dizisinden gerçek içeriği çıkar
        val isSensitiveHidden = content?.contains("Sensitive", ignoreCase = true) == true
                || content?.contains("content hidden", ignoreCase = true) == true
        if (isSensitiveHidden || (title.isNullOrBlank() && content.isNullOrBlank())) {
            try {
                val messages = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    extras?.getParcelableArray("android.messages", android.os.Parcelable::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    extras?.getParcelableArray("android.messages")
                }
                if (messages != null && messages.isNotEmpty()) {
                    val lastMsg = messages.last()
                    if (lastMsg is android.os.Bundle) {
                        val msgSender = lastMsg.getCharSequence("sender")?.toString()
                            ?: lastMsg.getCharSequence("sender_person")?.toString()
                        val msgText = lastMsg.getCharSequence("text")?.toString()

                        if (!msgText.isNullOrBlank()) {
                            content = msgText
                        }
                        if (!msgSender.isNullOrBlank() && title.isNullOrBlank()) {
                            title = msgSender
                        }
                        Log.d(TAG, "   🔓 MessagingStyle'dan çıkarıldı: sender='$msgSender', text='$msgText'")
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "   ⚠️ MessagingStyle parse hatası: ${e.message}")
            }
        }

        // Uygulama adı
        val appLabel = try {
            val pm = applicationContext.packageManager
            val appInfo = pm.getApplicationInfo(packageName, 0)
            pm.getApplicationLabel(appInfo).toString()
        } catch (e: Exception) {
            packageName.substringAfterLast('.').replaceFirstChar { it.uppercase() }
        }

        return NotificationInfo(
            key = key,
            packageName = packageName,
            appName = appLabel,
            title = title,
            content = content,
            timestamp = postTime,
            isGroupSummary = notification.flags and Notification.FLAG_GROUP_SUMMARY != 0
        )
    }
}
