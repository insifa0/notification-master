package com.notificationmaster.domain.usecase

import com.notificationmaster.domain.model.Rule
import com.notificationmaster.domain.repository.IRuleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Kural yönetimi UseCase'i.
 * UI'dan kural ekleme, silme, güncelleme, toggle işlemleri.
 */
class ManageRulesUseCase @Inject constructor(
    private val ruleRepository: IRuleRepository
) {

    /** Tüm kuralları getir (UI listesi için) */
    fun getAllRules(): Flow<List<Rule>> =
        ruleRepository.getAllRules()

    /** Yeni kural ekle */
    suspend fun addRule(rule: Rule): Long =
        ruleRepository.saveRule(rule)

    /** Belirli bir kuralı ID ile getir (edit için) */
    suspend fun getRuleById(ruleId: Long): Rule? =
        ruleRepository.getRuleById(ruleId)

    /** Kural sil */
    suspend fun deleteRule(ruleId: Long) =
        ruleRepository.deleteRule(ruleId)

    /** Kuralı aktif/pasif yap */
    suspend fun toggleRule(ruleId: Long, enabled: Boolean) =
        ruleRepository.toggleRule(ruleId, enabled)
}
