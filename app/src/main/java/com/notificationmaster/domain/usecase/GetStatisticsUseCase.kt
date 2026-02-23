package com.notificationmaster.domain.usecase

import com.notificationmaster.domain.model.Statistics
import com.notificationmaster.domain.repository.INotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * İstatistik UseCase'i — Dashboard'da gösterilecek veriler.
 */
class GetStatisticsUseCase @Inject constructor(
    private val notificationRepository: INotificationRepository
) {

    operator fun invoke(): Flow<Statistics> =
        notificationRepository.getStatistics()
}
