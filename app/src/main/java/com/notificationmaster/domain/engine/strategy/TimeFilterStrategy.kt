package com.notificationmaster.domain.engine.strategy

import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.model.Rule
import java.time.LocalTime
import javax.inject.Inject

/**
 * Zaman bazlı filtre stratejisi.
 * Belirli saat aralığında bildirimleri engeller.
 * Gece geçişini (ör: 22:00 - 07:00) destekler.
 */
class TimeFilterStrategy @Inject constructor() : IFilterStrategy {

    override fun canHandle(rule: Rule): Boolean =
        rule.filterType == FilterType.TIME_BASED

    override fun apply(notification: NotificationInfo, rule: Rule): FilterAction? {
        val startStr = rule.timeStart ?: return null
        val endStr = rule.timeEnd ?: return null

        return try {
            val now = LocalTime.now()
            val start = LocalTime.parse(startStr)
            val end = LocalTime.parse(endStr)

            val isInRange = if (start < end) {
                // Normal aralık: ör: 09:00 - 18:00
                now in start..end
            } else {
                // Gece geçişi: ör: 22:00 - 07:00
                now >= start || now <= end
            }

            // Paket adı da eşleşmeli
            val packageMatch = notification.packageName == rule.packageName

            if (isInRange && packageMatch) {
                rule.action
            } else {
                null
            }
        } catch (e: Exception) {
            // Geçersiz saat formatı — kuralı atla
            null
        }
    }
}
