package com.notificationmaster.di

import android.content.Context
import androidx.room.Room
import com.notificationmaster.data.local.db.AppDatabase
import com.notificationmaster.data.local.db.dao.NotificationHistoryDao
import com.notificationmaster.data.local.db.dao.RuleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "notification_master_db"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRuleDao(database: AppDatabase): RuleDao =
        database.ruleDao()

    @Provides
    fun provideNotificationHistoryDao(database: AppDatabase): NotificationHistoryDao =
        database.notificationHistoryDao()
}
