package com.notificationmaster.domain.model

/**
 * Bildirimden çıkarılan temiz veri modeli.
 * StatusBarNotification'dan dönüştürülerek oluşturulur.
 *
 * @param key Sistem bildirim key'i (cancelNotification için)
 * @param packageName Bildirim gönderen uygulamanın paket adı (ör: "com.whatsapp")
 * @param appName Uygulamanın görünen adı (ör: "WhatsApp")
 * @param title Bildirim başlığı
 * @param content Bildirim içeriği
 * @param timestamp Bildirim zamanı (milisaniye)
 * @param isGroupSummary WhatsApp gibi uygulamalarda özet bildirimi mi?
 */
data class NotificationInfo(
    val key: String,
    val packageName: String,
    val appName: String,
    val title: String?,
    val content: String?,
    val timestamp: Long,
    val isGroupSummary: Boolean = false
)
