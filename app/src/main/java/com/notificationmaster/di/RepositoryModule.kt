package com.notificationmaster.di

import com.notificationmaster.data.repository.NotificationRepositoryImpl
import com.notificationmaster.data.repository.RuleRepositoryImpl
import com.notificationmaster.domain.repository.INotificationRepository
import com.notificationmaster.domain.repository.IRuleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRuleRepository(impl: RuleRepositoryImpl): IRuleRepository

    @Binds
    @Singleton
    abstract fun bindNotificationRepository(impl: NotificationRepositoryImpl): INotificationRepository
}
