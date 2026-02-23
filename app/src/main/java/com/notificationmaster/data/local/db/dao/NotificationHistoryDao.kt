package com.notificationmaster.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.notificationmaster.data.local.db.entity.NotificationHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationHistoryDao {

    @Insert
    suspend fun insert(entity: NotificationHistoryEntity)

    @Query("SELECT * FROM notification_history ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    fun getHistory(limit: Int, offset: Int): Flow<List<NotificationHistoryEntity>>

    @Query("SELECT COUNT(*) FROM notification_history WHERE actionTaken != 'ALLOW'")
    fun getTotalBlocked(): Flow<Int>

    @Query("""
        SELECT COUNT(*) FROM notification_history 
        WHERE actionTaken != 'ALLOW' 
        AND timestamp >= :startOfDay
    """)
    fun getTodayBlocked(startOfDay: Long): Flow<Int>

    @Query("""
        SELECT packageName, appName, COUNT(*) as count 
        FROM notification_history 
        WHERE actionTaken != 'ALLOW'
        GROUP BY packageName 
        ORDER BY count DESC 
        LIMIT 5
    """)
    fun getTopBlockedApps(): Flow<List<AppBlockCountTuple>>

    @Query("DELETE FROM notification_history WHERE timestamp < :timestampMillis")
    suspend fun deleteOlderThan(timestampMillis: Long)

    @Query("DELETE FROM notification_history")
    suspend fun clearAll()
}

/**
 * Uygulama bazlı engelleme sayısı için Room tuple.
 */
data class AppBlockCountTuple(
    val packageName: String,
    val appName: String,
    val count: Int
)
