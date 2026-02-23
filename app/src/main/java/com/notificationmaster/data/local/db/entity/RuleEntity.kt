package com.notificationmaster.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Filtre kurallarının veritabanı tablosu.
 */
@Entity(tableName = "rules")
data class RuleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /** Hedef uygulamanın paket adı (ör: "com.whatsapp") */
    val packageName: String,

    /** Filtreleme türü: KEYWORD, APP_BLOCK, WHITELIST, TIME_BASED, CONTACT_WHITELIST */
    val filterType: String,

    /** Aranacak anahtar kelime (nullable — APP_BLOCK için gerekli değil) */
    val keyword: String? = null,

    /** Eylem: BLOCK, SILENT, ALLOW, RELAY */
    val action: String,

    /** Zaman bazlı kural için başlangıç saati (HH:mm) */
    val timeStart: String? = null,

    /** Zaman bazlı kural için bitiş saati (HH:mm) */
    val timeEnd: String? = null,

    /** İzin verilen kişi isimleri (virgülle ayrılmış, JSON yerine basit format) */
    val allowedContacts: String? = null,

    /** Kuralın aktif/pasif durumu */
    val isEnabled: Boolean = true,

    /** Oluşturulma tarihi */
    val createdAt: Long = System.currentTimeMillis()
)
