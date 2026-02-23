# Notification Master — ProGuard Rules

# Stack trace okunabilirliği için satır numaralarını koru
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ─── Room ───
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers class * {
    @androidx.room.* <fields>;
    @androidx.room.* <methods>;
}
-dontwarn androidx.room.paging.**

# ─── Hilt / Dagger ───
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }
-dontwarn dagger.hilt.internal.**

# ─── NotificationListenerService (SİSTEM SERVİSİ — DOKUNMA!) ───
-keep class com.notificationmaster.service.NotificationListener { *; }

# ─── WorkManager / HiltWorker ───
-keep class com.notificationmaster.data.worker.** { *; }
-keep class * extends androidx.work.Worker
-keep class * extends androidx.work.CoroutineWorker

# ─── Compose ───
-dontwarn androidx.compose.**

# ─── Accompanist ───
-dontwarn com.google.accompanist.**

# ─── Coroutines ───
-dontwarn kotlinx.coroutines.**
-keepclassmembers class kotlinx.coroutines.** { *; }

# ─── Kotlin Serialization (gelecekte gerekebilir) ───
-keepattributes *Annotation*
-keepclassmembers class * {
    @kotlin.Metadata *;
}

# ─── Enum koruma ───
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}