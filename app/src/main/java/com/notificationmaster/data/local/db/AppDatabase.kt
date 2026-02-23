package com.notificationmaster.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.notificationmaster.data.local.db.dao.NotificationHistoryDao
import com.notificationmaster.data.local.db.dao.RuleDao
import com.notificationmaster.data.local.db.entity.NotificationHistoryEntity
import com.notificationmaster.data.local.db.entity.RuleEntity

@Database(
    entities = [
        RuleEntity::class,
        NotificationHistoryEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ruleDao(): RuleDao
    abstract fun notificationHistoryDao(): NotificationHistoryDao

    companion object {
        /**
         * v1 → v2: rules tablosuna allowedContacts sütunu eklendi.
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE rules ADD COLUMN allowedContacts TEXT")
            }
        }
    }
}
