package com.notificationmaster.data.repository

import com.notificationmaster.data.local.db.dao.NotificationHistoryDao
import com.notificationmaster.data.local.db.entity.NotificationHistoryEntity
import com.notificationmaster.data.local.mapper.toDomain
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.NotificationHistory
import com.notificationmaster.domain.model.Statistics
import com.notificationmaster.domain.repository.INotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val historyDao: NotificationHistoryDao
) : INotificationRepository {

    override suspend fun logNotification(
        packageName: String,
        appName: String,
        title: String?,
        contentPreview: String?,
        actionTaken: FilterAction,
        matchedRuleId: Long?
    ) {
        historyDao.insert(
            NotificationHistoryEntity(
                packageName = packageName,
                appName = appName,
                title = title,
                contentPreview = contentPreview,
                actionTaken = actionTaken.name,
                matchedRuleId = matchedRuleId
            )
        )
    }

    override fun getHistory(limit: Int, offset: Int): Flow<List<NotificationHistory>> =
        historyDao.getHistory(limit, offset).map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getStatistics(): Flow<Statistics> {
        val startOfDay = LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        return combine(
            historyDao.getTotalBlocked(),
            historyDao.getTodayBlocked(startOfDay),
            historyDao.getTopBlockedApps()
        ) { total, today, topApps ->
            Statistics(
                totalBlocked = total,
                totalToday = today,
                topBlockedApps = topApps.map { it.toDomain() }
            )
        }
    }

    override suspend fun clearHistory() =
        historyDao.clearAll()

    override suspend fun deleteOlderThan(timestampMillis: Long) =
        historyDao.deleteOlderThan(timestampMillis)
}
