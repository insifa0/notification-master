package com.notificationmaster.domain.engine.strategy

import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.model.Rule
import javax.inject.Inject

/**
 * Anahtar kelime tabanlı filtre stratejisi.
 * Bildirim başlığı veya içeriğinde belirtilen kelimeyi arar.
 * Case-insensitive (büyük/küçük harf duyarsız).
 *
 * Çoklu keyword desteği: virgülle ayrılmış kelimelerden herhangi biri eşleşirse tetiklenir.
 * Örnek: "reklam, kampanya, indirim" → herhangi biri varsa eşleşir.
 */
class KeywordFilterStrategy @Inject constructor() : IFilterStrategy {

    override fun canHandle(rule: Rule): Boolean =
        rule.filterType == FilterType.KEYWORD

    override fun apply(notification: NotificationInfo, rule: Rule): FilterAction? {
        val keywordStr = rule.keyword ?: return null

        // Virgülle ayrılmış çoklu keyword desteği
        val keywords = keywordStr.split(",").map { it.trim() }.filter { it.isNotBlank() }
        if (keywords.isEmpty()) return null

        val title = notification.title ?: ""
        val content = notification.content ?: ""

        // Herhangi bir keyword eşleşirse tetikle
        val matched = keywords.any { keyword ->
            title.contains(keyword, ignoreCase = true) ||
            content.contains(keyword, ignoreCase = true)
        }

        return if (matched) {
            rule.action  // BLOCK veya SILENT
        } else {
            null  // Bu kural eşleşmedi
        }
    }
}
