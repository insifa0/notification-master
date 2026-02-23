package com.notificationmaster.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Engellenen bildirim geçmişi tablosu.
 */
@Entity(tableName = "notification_history")
data class NotificationHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /** Bildirim gönderen uygulamanın paket adı */
    val packageName: String,

    /** Uygulamanın görünen adı (ör: "WhatsApp") */
    val appName: String,

    /** Bildirim başlığı */
    val title: String?,

    /** Bildirim içeriği (kısaltılmış — maks 100 karakter) */
    val contentPreview: String?,

    /** Uygulanan eylem: BLOCKED, SILENCED */
    val actionTaken: String,

    /** Bu kararı tetikleyen kuralın ID'si */
    val matchedRuleId: Long?,

    /** Bildirim zamanı */
    val timestamp: Long = System.currentTimeMillis()
)
