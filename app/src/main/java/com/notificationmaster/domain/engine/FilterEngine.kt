package com.notificationmaster.domain.engine

import com.notificationmaster.domain.engine.strategy.IFilterStrategy
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterResult
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.model.Rule
import javax.inject.Inject

/**
 * Filtre Motoru — Bildirimleri kurallara göre değerlendirir.
 *
 * Strategy Pattern kullanarak her filtre türünü ayrı sınıflarda izole eder.
 *
 * Öncelik sırası:
 * 1. WHITELIST kuralları (eşleşirse → ALLOW)
 * 2. CONTACT_WHITELIST kuralları (kişi bazlı filtreleme)
 * 3. KEYWORD kuralları
 * 4. APP_BLOCK kuralları
 * 5. TIME_BASED kuralları
 * 6. Hiçbir kural eşleşmezse → ALLOW
 */
class FilterEngine @Inject constructor(
    private val strategies: Set<@JvmSuppressWildcards IFilterStrategy>
) {

    fun evaluate(
        notification: NotificationInfo,
        rules: List<Rule>
    ): FilterResult {
        val activeRules = rules.filter { it.isEnabled }

        // 1. Whitelist kontrolü — direkt ALLOW
        val whitelistRules = activeRules.filter { it.filterType == FilterType.WHITELIST }
        for (rule in whitelistRules) {
            if (notification.packageName == rule.packageName) {
                return FilterResult(
                    action = FilterAction.ALLOW,
                    matchedRule = rule,
                    reason = "Whitelist eşleşmesi: ${rule.packageName}"
                )
            }
        }

        // 2. Contact Whitelist — kişi bazlı, strateji üzerinden
        val contactRules = activeRules.filter { it.filterType == FilterType.CONTACT_WHITELIST }
        for (rule in contactRules) {
            if (notification.packageName == rule.packageName) {
                val strategy = strategies.find { it.canHandle(rule) } ?: continue
                val action = strategy.apply(notification, rule)
                if (action != null) {
                    return FilterResult(
                        action = action,
                        matchedRule = rule,
                        reason = if (action == FilterAction.ALLOW) {
                            "Kişi whitelist eşleşmesi: ${notification.title}"
                        } else {
                            "Kişi whitelist dışı — engellendi: ${notification.title}"
                        }
                    )
                }
            }
        }

        // 3. Diğer kuralları sırayla kontrol et
        val blockRules = activeRules.filter {
            it.filterType != FilterType.WHITELIST && it.filterType != FilterType.CONTACT_WHITELIST
        }
        for (rule in blockRules) {
            val strategy = strategies.find { it.canHandle(rule) } ?: continue
            val action = strategy.apply(notification, rule)
            if (action != null) {
                return FilterResult(
                    action = action,
                    matchedRule = rule,
                    reason = "${rule.filterType} kuralı eşleşti: ${rule.keyword ?: rule.packageName}"
                )
            }
        }

        // 4. Default: ALLOW
        return FilterResult(
            action = FilterAction.ALLOW,
            matchedRule = null,
            reason = "Eşleşen kural yok"
        )
    }
}
