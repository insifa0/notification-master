package com.notificationmaster.domain.repository

import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.NotificationHistory
import com.notificationmaster.domain.model.Statistics
import kotlinx.coroutines.flow.Flow

/**
 * Bildirim geçmişi ve istatistikler için repository interface'i.
 * Data layer'da Room ile implement edilecek.
 */
interface INotificationRepository {

    /** Engellenen/işlenen bildirimi geçmişe kaydet */
    suspend fun logNotification(
        packageName: String,
        appName: String,
        title: String?,
        contentPreview: String?,
        actionTaken: FilterAction,
        matchedRuleId: Long?
    )

    /** Bildirim geçmişini getir (pagination) */
    fun getHistory(limit: Int = 50, offset: Int = 0): Flow<List<NotificationHistory>>

    /** İstatistikleri getir */
    fun getStatistics(): Flow<Statistics>

    /** Tüm geçmişi temizle */
    suspend fun clearHistory()

    /** Belirli bir tarihten eski kayıtları sil */
    suspend fun deleteOlderThan(timestampMillis: Long)
}
