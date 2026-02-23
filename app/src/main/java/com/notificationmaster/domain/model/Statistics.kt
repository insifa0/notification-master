package com.notificationmaster.domain.model

/**
 * Dashboard'da gösterilecek istatistik verileri.
 */
data class Statistics(
    val totalBlocked: Int = 0,
    val totalToday: Int = 0,
    val topBlockedApps: List<AppBlockCount> = emptyList(),
    val hourlyDistribution: Map<Int, Int> = emptyMap()
)

/**
 * Uygulama bazında engelleme sayısı.
 */
data class AppBlockCount(
    val packageName: String,
    val appName: String,
    val count: Int
)

/**
 * Engellenen bildirim geçmişi kaydı.
 */
data class NotificationHistory(
    val id: Long = 0,
    val packageName: String,
    val appName: String,
    val title: String?,
    val contentPreview: String?,
    val actionTaken: FilterAction,
    val matchedRuleId: Long?,
    val timestamp: Long
)
