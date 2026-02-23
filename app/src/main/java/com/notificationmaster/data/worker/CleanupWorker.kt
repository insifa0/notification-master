package com.notificationmaster.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.notificationmaster.domain.repository.INotificationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Eski bildirim geçmişini temizleyen WorkManager worker'ı.
 *
 * Varsayılan olarak 30 günden eski kayıtları siler.
 * Günde bir kez çalışır (PeriodicWorkRequest).
 */
@HiltWorker
class CleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val notificationRepository: INotificationRepository
) : CoroutineWorker(context, params) {

    companion object {
        const val TAG = "CleanupWorker"
        const val WORK_NAME = "notification_history_cleanup"
        const val DEFAULT_DAYS_TO_KEEP = 30L
    }

    override suspend fun doWork(): Result {
        return try {
            val cutoffTime = System.currentTimeMillis() - (DEFAULT_DAYS_TO_KEEP * 24 * 60 * 60 * 1000)
            notificationRepository.deleteOlderThan(cutoffTime)
            Log.d(TAG, "✅ Eski bildirim geçmişi temizlendi (${DEFAULT_DAYS_TO_KEEP} günden eski)")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Temizleme hatası: ${e.message}", e)
            Result.retry()
        }
    }
}
