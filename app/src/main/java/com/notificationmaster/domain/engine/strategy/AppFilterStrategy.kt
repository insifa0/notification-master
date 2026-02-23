package com.notificationmaster.domain.engine.strategy

import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.model.Rule
import javax.inject.Inject

/**
 * Uygulama bazlı filtre stratejisi.
 * Belirli bir uygulamanın TÜM bildirimlerini engeller.
 * Paket adı eşleşmesi yapar.
 */
class AppFilterStrategy @Inject constructor() : IFilterStrategy {

    override fun canHandle(rule: Rule): Boolean =
        rule.filterType == FilterType.APP_BLOCK

    override fun apply(notification: NotificationInfo, rule: Rule): FilterAction? {
        return if (notification.packageName == rule.packageName) {
            rule.action  // Paket adı eşleşti → engelle
        } else {
            null
        }
    }
}
