package com.notificationmaster.data.repository

import com.notificationmaster.data.local.db.dao.RuleDao
import com.notificationmaster.data.local.mapper.toDomain
import com.notificationmaster.data.local.mapper.toEntity
import com.notificationmaster.domain.model.Rule
import com.notificationmaster.domain.repository.IRuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RuleRepositoryImpl @Inject constructor(
    private val ruleDao: RuleDao
) : IRuleRepository {

    override fun getAllActiveRules(): Flow<List<Rule>> =
        ruleDao.getActiveRules().map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getRulesForPackage(packageName: String): Flow<List<Rule>> =
        ruleDao.getRulesForPackage(packageName).map { entities ->
            entities.map { it.toDomain() }
        }

    override fun getAllRules(): Flow<List<Rule>> =
        ruleDao.getAllRules().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun saveRule(rule: Rule): Long =
        ruleDao.insertRule(rule.toEntity())

    override suspend fun getRuleById(ruleId: Long): Rule? =
        ruleDao.getRuleById(ruleId)?.toDomain()

    override suspend fun deleteRule(ruleId: Long) =
        ruleDao.deleteRule(ruleId)

    override suspend fun toggleRule(ruleId: Long, enabled: Boolean) =
        ruleDao.toggleRule(ruleId, enabled)
}
