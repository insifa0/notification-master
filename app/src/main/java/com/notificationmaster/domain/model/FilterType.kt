package com.notificationmaster.domain.model

/**
 * Filtre türlerini tanımlayan enum.
 */
enum class FilterType {
    /** Anahtar kelimeye göre filtreleme (başlık veya içerikte arama) */
    KEYWORD,

    /** Tüm uygulamayı engelleme (paket adına göre) */
    APP_BLOCK,

    /** Sadece bu uygulamadan gelen bildirimlere izin ver */
    WHITELIST,

    /** Belirli zaman aralığında engelleme */
    TIME_BASED,

    /** Sadece belirli kişilerden gelen bildirimlere izin ver, geri kalanı engelle */
    CONTACT_WHITELIST
}

/**
 * Filtre motorunun uygulayacağı eylem türleri.
 */
enum class FilterAction {
    /** Bildirimi tamamen sil (notification bar'dan kaldır) */
    BLOCK,

    /** Bildirimi sessizce göster (ses/titreşim yok) */
    SILENT,

    /** Bildirimi olduğu gibi bırak */
    ALLOW,

    /** Orijinali koru, içeriğin kopyasını bizim bildirim olarak oluştur (akıllı saat için) */
    RELAY
}
