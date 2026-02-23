package com.notificationmaster.domain.model

/**
 * Kullanıcının oluşturduğu filtre kuralı.
 *
 * @param id Kuralın benzersiz kimliği (veritabanından)
 * @param packageName Hedef uygulamanın paket adı (ör: "com.whatsapp")
 * @param filterType Filtreleme türü
 * @param keyword Aranacak anahtar kelime (sadece KEYWORD türü için)
 * @param action Eşleşme durumunda uygulanacak eylem
 * @param timeStart Zaman bazlı kural için başlangıç saati (HH:mm) — sadece TIME_BASED için
 * @param timeEnd Zaman bazlı kural için bitiş saati (HH:mm) — sadece TIME_BASED için
 * @param allowedContacts İzin verilen kişi isimleri listesi (sadece CONTACT_WHITELIST için)
 * @param isEnabled Kuralın aktif/pasif durumu
 * @param createdAt Oluşturulma tarihi (milisaniye)
 */
data class Rule(
    val id: Long = 0,
    val packageName: String,
    val filterType: FilterType,
    val keyword: String? = null,
    val action: FilterAction = FilterAction.BLOCK,
    val timeStart: String? = null,
    val timeEnd: String? = null,
    val allowedContacts: List<String> = emptyList(),
    val isEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
