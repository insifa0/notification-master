package com.notificationmaster.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.notificationmaster.R
import com.notificationmaster.domain.model.NotificationInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Relay (Yansıtma) bildirimi oluşturmaktan sorumlu yönetici.
 *
 * Orijinal bildirimi SİLMEZ — sadece içeriğinin kopyasını
 * Notification Master'ın kendi bildirimi olarak oluşturur.
 * Akıllı saatte WhatsApp kapalı + Notification Master açık olduğunda,
 * sadece bu kopya bildirimler saate ulaşır.
 */
@Singleton
class NotificationRelayManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val CHANNEL_ID = "relay_channel"
        private const val CHANNEL_NAME = "Yansıtılan Bildirimler"
        private const val CHANNEL_DESC = "Filtreden geçen bildirimlerin kopyaları"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createChannel()
    }

    /**
     * Bildirim kanalı oluştur — ses ve titreşim açık.
     * Akıllı saate ulaşması için kanalın importance HIGH olması gerekir.
     */
    private fun createChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = CHANNEL_DESC
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * Filtreden geçen bildirimin kopyasını oluştur.
     *
     * @param info Orijinal bildirimden çıkarılan bilgiler
     */
    fun relay(info: NotificationInfo) {
        // Her bildirim key'i için benzersiz ID
        val notificationId = info.key.hashCode()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(info.title ?: info.appName)
            .setContentText(info.content)
            .setSubText(info.appName)
            .setAutoCancel(true)
            .setWhen(info.timestamp)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            // Grup bildirimi: aynı uygulamadan birden fazla relay gelirse grupla
            .setGroup("relay_${info.packageName}")
            .build()

        notificationManager.notify(notificationId, notification)

        // Grup özet bildirimi — 2+ bildirim varsa otomatik gruplanır
        val summaryNotification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setSubText(info.appName)
            .setGroup("relay_${info.packageName}")
            .setGroupSummary(true)
            .setAutoCancel(true)
            .build()

        notificationManager.notify("relay_summary_${info.packageName}".hashCode(), summaryNotification)
    }
}
