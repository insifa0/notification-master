package com.notificationmaster.domain.model

/**
 * Filtre motorunun kararını temsil eder.
 *
 * @param action Uygulanacak eylem (BLOCK, SILENT, ALLOW)
 * @param matchedRule Bu kararı tetikleyen kural (null = hiçbir kural eşleşmedi)
 * @param reason Kararın insan-okunabilir açıklaması (debug/geçmiş için)
 */
data class FilterResult(
    val action: FilterAction,
    val matchedRule: Rule? = null,
    val reason: String = ""
)
