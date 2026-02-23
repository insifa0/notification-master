package com.notificationmaster.di

import com.notificationmaster.domain.engine.strategy.AppFilterStrategy
import com.notificationmaster.domain.engine.strategy.ContactWhitelistStrategy
import com.notificationmaster.domain.engine.strategy.IFilterStrategy
import com.notificationmaster.domain.engine.strategy.KeywordFilterStrategy
import com.notificationmaster.domain.engine.strategy.TimeFilterStrategy
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

/**
 * Filtre stratejilerini Hilt ile Set olarak inject eder.
 * FilterEngine, tüm stratejileri Set<IFilterStrategy> olarak alır.
 * Yeni strateji eklemek için buraya bir @Binds @IntoSet satırı eklemen yeterli.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FilterModule {

    @Binds
    @IntoSet
    abstract fun bindKeywordStrategy(impl: KeywordFilterStrategy): IFilterStrategy

    @Binds
    @IntoSet
    abstract fun bindAppStrategy(impl: AppFilterStrategy): IFilterStrategy

    @Binds
    @IntoSet
    abstract fun bindTimeStrategy(impl: TimeFilterStrategy): IFilterStrategy

    @Binds
    @IntoSet
    abstract fun bindContactWhitelistStrategy(impl: ContactWhitelistStrategy): IFilterStrategy
}
