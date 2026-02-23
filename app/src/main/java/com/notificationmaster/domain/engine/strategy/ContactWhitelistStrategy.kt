package com.notificationmaster.domain.engine.strategy

import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.model.Rule
import javax.inject.Inject

/**
 * Kişi Bazlı Whitelist Stratejisi.
 *
 * Bir uygulamadan (ör: WhatsApp) gelen bildirimlerde:
 * - Gönderici ismi izin listesinde → ALLOW
 * - Gönderici ismi izin listesinde değil → BLOCK
 *
 * WhatsApp bildirim yapısı:
 * - Bireysel: title = "Annem", text = "Mesaj..."
 * - Grup: title = "Grup Adı", text = "Annem: Mesaj..."
 *
 * Bu strateji her iki durumu da kontrol eder.
 */
class ContactWhitelistStrategy @Inject constructor() : IFilterStrategy {

    override fun canHandle(rule: Rule): Boolean =
        rule.filterType == FilterType.CONTACT_WHITELIST

    override fun apply(notification: NotificationInfo, rule: Rule): FilterAction? {
        // Paket adı eşleşmeli
        if (notification.packageName != rule.packageName) return null

        val allowedContacts = rule.allowedContacts
        if (allowedContacts.isEmpty()) return null

        val title = notification.title ?: ""
        val content = notification.content ?: ""

        // 1. Bireysel mesaj: title = kişi ismi
        val isTitleMatch = allowedContacts.any { contact ->
            title.equals(contact, ignoreCase = true)
        }
        if (isTitleMatch) return FilterAction.ALLOW

        // 2. Grup mesajı: content = "Kişi: mesaj..."
        val isContentMatch = allowedContacts.any { contact ->
            content.startsWith("$contact:", ignoreCase = true) ||
            content.startsWith("$contact :", ignoreCase = true)
        }
        if (isContentMatch) return FilterAction.ALLOW

        // 3. İzin listesinde değil → Engelle
        return rule.action
    }
}
