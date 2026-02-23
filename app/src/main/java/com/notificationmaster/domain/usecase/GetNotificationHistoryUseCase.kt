package com.notificationmaster.domain.usecase

import com.notificationmaster.domain.model.NotificationHistory
import com.notificationmaster.domain.repository.INotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Bildirim geçmişi UseCase'i — Geçmiş ekranında gösterilecek veriler.
 */
class GetNotificationHistoryUseCase @Inject constructor(
    private val notificationRepository: INotificationRepository
) {

    operator fun invoke(limit: Int = 50, offset: Int = 0): Flow<List<NotificationHistory>> =
        notificationRepository.getHistory(limit, offset)

    /** Tüm geçmişi temizle */
    suspend fun clearHistory() =
        notificationRepository.clearHistory()
}
