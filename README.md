# 🛡️ Notification Master

**Notification Master**, Android 13+ uyumlu, modern ve gizlilik odaklı bir bildirim yönetim ve filtreleme uygulamasıdır. Gün içinde dikkat dağıtan reklamları, gereksiz duyuruları ve istemediğiniz uygulamaların bildirimlerini arka planda sessizce engeller veya gizler.

Modern **Jetpack Compose** kullanılarak, tamamı **Dark Theme (Karanlık Tema)** konseptiyle tasarlandı. 

---

## 🏗️ Proje Mimarisi ve Teknoloji Yığıtı (Tech Stack)

Uygulama, sürdürülebilirliği sağlamak ve test edilebilirliği artırmak için **Clean Architecture (Temiz Mimari)** ve **MVVM (Model-View-ViewModel)** prensiplerine uygun olarak geliştirilmiştir.

*   **UI (Kullanıcı Arayüzü):** Jetpack Compose, Material Design 3
*   **Programlama Dili:** Kotlin
*   **Asenkron İşlemler:** Kotlin Coroutines & Flow
*   **Dependency Injection (Bağımlılık Enjeksiyonu):** Dagger Hilt
*   **Yerel Veritabanı:** Room Database
*   **Arka Plan İşlemleri:** `NotificationListenerService` (Bildirim Dinleyici), WorkManager
*   **Navigasyon:** Jetpack Compose Navigation

---

## ✨ Temel Özellikler

### 1. 🎯 App Block (Uygulama Engelleme)
Seçtiğiniz uygulamalardan gelen *tüm* bildirimleri koşulsuz şartsız engeller. Oyunlardan veya alışveriş uygulamalarından gelen sürekli bildirimleri kesmek için idealdir. 
*(Not: Android 11+ App Visibility kısıtlamaları `QUERY_ALL_PACKAGES` izni ile yönetilerek WhatsApp, Telegram gibi tüm cihaz uygulamaları desteklenmektedir.)*

### 2. 🔤 Keyword Filter (Kelime Bazlı Filtre)
Gelen bildirimlerin başlık veya içeriğinde sizin belirlediğiniz kelimeler (örn: "indirim", "fırsat", "kampanya") geçiyorsa, bu bildirimleri yakalar ve ekranda göstermeden yok eder.

> ⚠️ **Mesajlaşma Uygulamaları Hakkında Uyarı:** Samsung, Xiaomi gibi bazı cihaz üreticileri ve Android'in yerleşik gizlilik ilkeleri, kilit ekranında veya genel bildirimlerde mesaj içeriklerini gizler ("Sensitive notification content hidden"). İçerik sistem tarafından gizlendiğinde, "Keyword" filtresi hedef kelimeyi okuyamayacağı için çalışmayabilir. Mesajlaşma uygulamaları için **App Block** kullanılması önerilir.

### 3. 🕒 Time-Based Block (Zaman Ayarlı Engel - Schedule)
Belirlediğiniz uygulamaların gönderdiği bildirimleri sadece sizin seçtiğiniz saat aralıklarında (örn: Gece 23:00 ile Sabah 07:00 arası) engeller. Uyku veya odaklanma saatleri için mükemmeldir.

### 4. 📇 Contact Whitelist (Özel Kişilere İzin Verme)
Belirli bir uygulamadan (örneğin WhatsApp) gelen *tüm bildirmleri* engellerken, *sadece sizin belirlediğiniz kişilerden* (Örn: "Annem", "Patron") gelen mesajların ekrana düşmesine izin verir.

### 5. 🔕 Mute / Silent (Sessize Alma)
Bildirimi tamamen yok etmek yerine, sadece titreşimini ve sesini kapatarak sessizce bildirim tepsisinde toplanmasını sağlar.

### 6. 📊 Dashboard (Gelişmiş İstatistikler)
Bugüne kadar toplam kaç bildirimin engellendiğini, odaklanarak ne kadar zaman kazandığınızı yüzdelik artış/azalış trendleriyle grafiksel olarak sunar. Hangi uygulamanın sizi en çok rahatsız ettiğini "Top Offenders (En Çok Rahatsız Edenler)" listesiyle gösterir.

### 7. ⏱️ Notification Relay (Gelecek Özellik / ArGe)
Engellenen bildirimlerin içeriklerinin ileride özetlenmesi veya farklı bir platforma yansıtılması için altyapı hazırlanmıştır.

---

## 🛠️ Kurulum Bilgileri

Proje, standart bir Android Studio projesidir.

1.  Projeyi klonlayın:
    ```bash
    git clone https://github.com/insifa0/notification-master.git
    ```
2.  **Android Studio**'da açın (Koala Feature Drop veya daha güncel bir sürüm önerilir).
3.  Gradle senkronizasyonunun bitmesini bekleyin.
4.  Cihazınızı (USB Hata Ayıklama açık) bağlayın veya Emülatör başlatın.
5.  `Run` butonuna tıklayarak derleyin.

> **Önemli İzinler:** Uygulamanın çalışabilmesi için cihazınızın ayarlarından `Notification Access` (Bildirim Okuma) izninin manuel olarak uygulamanın kendisine verilmesi gerekmektedir. Uygulama açılışta bu izni sizden isteyecektir.

---

## 📝 Gelecek Geliştirmeler (Roadmap)
*   **History (Geçmiş) Ekranı Tasarım Güncellemesi:** Engellenen bildirimlerin tarihlere göre gruplanarak detaylıca listelenmesi (Şu anda geliştirme aşamasındadır - Faz 7).
*   **Bildirim Özetleri (AI Entegrasyonu):** Engellenen bildirimlerin gün sonunda özet halinde kullanıcıya sunulması.
*   **Gelişmiş Filtre Test Aracı:** Kural oluştururken o kuralın "örnek" bir bildirimi tutup tutmayacağını test eden ekran.

---

## 👨‍💻 Geliştirici
Geliştirme süreci, kod kalitesi ve mimari kararlar ön planda tutularak **@insifa0** tarafından yönetilmektedir.
