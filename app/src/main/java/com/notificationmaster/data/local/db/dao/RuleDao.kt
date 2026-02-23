package com.notificationmaster.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.notificationmaster.data.local.db.entity.RuleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RuleDao {

    @Query("SELECT * FROM rules WHERE isEnabled = 1")
    fun getActiveRules(): Flow<List<RuleEntity>>

    @Query("SELECT * FROM rules WHERE packageName = :packageName")
    fun getRulesForPackage(packageName: String): Flow<List<RuleEntity>>

    @Query("SELECT * FROM rules ORDER BY createdAt DESC")
    fun getAllRules(): Flow<List<RuleEntity>>

    @Query("SELECT * FROM rules WHERE id = :ruleId")
    suspend fun getRuleById(ruleId: Long): RuleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRule(rule: RuleEntity): Long

    @Query("DELETE FROM rules WHERE id = :ruleId")
    suspend fun deleteRule(ruleId: Long)

    @Query("UPDATE rules SET isEnabled = :enabled WHERE id = :ruleId")
    suspend fun toggleRule(ruleId: Long, enabled: Boolean)
}
