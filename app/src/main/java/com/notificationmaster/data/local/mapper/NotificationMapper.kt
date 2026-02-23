package com.notificationmaster.data.local.mapper

import com.notificationmaster.data.local.db.dao.AppBlockCountTuple
import com.notificationmaster.data.local.db.entity.NotificationHistoryEntity
import com.notificationmaster.domain.model.AppBlockCount
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.NotificationHistory

/**
 * NotificationHistoryEntity → NotificationHistory (Domain) dönüşümü.
 */
fun NotificationHistoryEntity.toDomain(): NotificationHistory = NotificationHistory(
    id = id,
    packageName = packageName,
    appName = appName,
    title = title,
    contentPreview = contentPreview,
    actionTaken = FilterAction.valueOf(actionTaken),
    matchedRuleId = matchedRuleId,
    timestamp = timestamp
)

/**
 * AppBlockCountTuple → AppBlockCount (Domain) dönüşümü.
 */
fun AppBlockCountTuple.toDomain(): AppBlockCount = AppBlockCount(
    packageName = packageName,
    appName = appName,
    count = count
)
