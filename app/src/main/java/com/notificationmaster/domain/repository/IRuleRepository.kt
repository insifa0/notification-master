package com.notificationmaster.domain.repository

import com.notificationmaster.domain.model.Rule
import kotlinx.coroutines.flow.Flow

/**
 * Filtre kuralları için repository interface'i.
 * Data layer'da Room ile implement edilecek.
 */
interface IRuleRepository {

    /** Tüm aktif kuralları getir (filtreleme için) */
    fun getAllActiveRules(): Flow<List<Rule>>

    /** Belirli bir uygulamaya ait kuralları getir */
    fun getRulesForPackage(packageName: String): Flow<List<Rule>>

    /** Tüm kuralları getir (UI listesi için) */
    fun getAllRules(): Flow<List<Rule>>

    /** Yeni kural ekle veya güncelle */
    suspend fun saveRule(rule: Rule): Long

    /** Belirli bir kuralı ID ile getir */
    suspend fun getRuleById(ruleId: Long): Rule?

    /** Kural sil */
    suspend fun deleteRule(ruleId: Long)

    /** Kuralı aktif/pasif yap */
    suspend fun toggleRule(ruleId: Long, enabled: Boolean)
}
