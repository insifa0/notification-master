# 🏆 Top 5 Proje — Kapsamlı Karşılaştırma

> **Kriter**: 2 kişilik ekip, heavy hitter, kariyer etkisi  
> **Tarih**: 2026-02-25

---

## 📊 Genel Karşılaştırma Tablosu

| Kategori                | Distributed FS | Container Runtime | Message Queue ⭐ | CDN/Object Storage | WebAssembly Runtime |
| ----------------------- | :------------: | :---------------: | :-------------: | :----------------: | :-----------------: |
| **Seviye**              |   4 (Uzman)    |     3 (İleri)     |    3 (İleri)    |     4 (Uzman)      |      3 (İleri)      |
| **Süre**                |  10-12 hafta   |     6-8 hafta     |   8-10 hafta    |     8-10 hafta     |      6-8 hafta      |
| **Ekip uyumu**          |   ✅ Mükemmel   |    ✅ Mükemmel     |      ✅ İyi      |     ✅ Mükemmel     |     ✅ Mükemmel      |
| **Zorluk**              |     ⭐⭐⭐⭐⭐      |       ⭐⭐⭐⭐        |       ⭐⭐⭐       |        ⭐⭐⭐⭐        |        ⭐⭐⭐⭐         |
| **CV etkisi**           |     🔥🔥🔥🔥🔥      |       🔥🔥🔥🔥        |      🔥🔥🔥🔥       |        🔥🔥🔥🔥        |        🔥🔥🔥🔥         |
| **Mülakat değeri**      |     ⭐⭐⭐⭐⭐      |       ⭐⭐⭐⭐        |      ⭐⭐⭐⭐⭐      |        ⭐⭐⭐⭐        |         ⭐⭐⭐         |
| **Başlangıç kolaylığı** |      Zor       |       Orta        |     ✅ Kolay     |        Orta        |        Orta         |

---

## 🎯 Detaylı Karşılaştırma

### 1. Teknik Derinlik

| Proje                 | Öğrenilen Kavramlar                                                                |
| --------------------- | ---------------------------------------------------------------------------------- |
| **Distributed FS**    | GFS/HDFS mimarisi, block replication, NameNode/DataNode, heartbeat, fault recovery |
| **Container Runtime** | Linux namespaces, cgroups, OverlayFS, OCI spec                                     |
| **Message Queue** ⭐   | Log-based storage, partitioning, consumer groups, offset tracking, pub/sub         |
| **CDN/Obj. Storage**  | Consistent hashing, geo-distributed cache, S3 API, cache invalidation              |
| **WASM Runtime**      | Binary parsing, stack machine, type system, WASI interface                         |

---

### 2. Ekip Bölünmesi

| Proje                 | Kişi 1                    | Kişi 2                     | Bağımsızlık                  |
| --------------------- | ------------------------- | -------------------------- | ---------------------------- |
| **Distributed FS**    | NameNode (metadata)       | DataNode (block storage)   | ✅ Yüksek                     |
| **Container Runtime** | Runtime / Namespace Layer | Image / OverlayFS Layer    | ✅ Yüksek                     |
| **Message Queue** ⭐   | Producer + Broker         | Consumer + Consumer Groups | ⚠️ Orta (broker paylaşılıyor) |
| **CDN/Obj. Storage**  | Edge Cache Layer          | Origin / Object Storage    | ✅ Yüksek                     |
| **WASM Runtime**      | Binary Parser             | Stack Machine Executor     | ✅ Yüksek                     |

---

### 3. Hangi Şirketlere Kapı Açıyor?

| Proje                 | Hedef Şirketler/Alanlar                                                        |
| --------------------- | ------------------------------------------------------------------------------ |
| **Distributed FS**    | Google (Colossus), Hadoop, Databricks, Snowflake, Netflix                      |
| **Container Runtime** | Docker, Kubernetes/GKE, AWS ECS/EKS, Cloudflare Workers                        |
| **Message Queue** ⭐   | LinkedIn (Kafka yazan yer), Uber, Lyft, her Fintech, her microservice mimarisi |
| **CDN/Obj. Storage**  | Cloudflare, AWS (S3/CloudFront), Akamai, Fastly                                |
| **WASM Runtime**      | Cloudflare Workers, Fastly Compute@Edge, Wasmer, Wasmtime                      |

---

### 4. Mülakat Değeri

| Proje                 | Yaygın Mülakat Soruları                                                                  |
| --------------------- | ---------------------------------------------------------------------------------------- |
| **Distributed FS**    | "GFS nasıl çalışır?", "Replication stratejileri?"                                        |
| **Container Runtime** | "Docker'ın altında ne var?", "Namespace vs VM farkı?"                                    |
| **Message Queue** ⭐   | "Kafka nasıl çalışır?", "Exactly-once delivery nasıl sağlanır?", "Consumer group nedir?" |
| **CDN/Obj. Storage**  | "Cache invalidation nasıl yapılır?", "Consistent hashing nedir?"                         |
| **WASM Runtime**      | "WASM nedir?", "Container vs WASM farkı?"                                                |

---

### 5. Öğrenme Eğrisi

```
Distributed FS      ████████████████████ (En Dik)
Container Runtime   ████████████████░░░░
CDN/Obj Storage     ████████████████░░░░
WASM Runtime        ██████████████░░░░░░
Message Queue  ⭐    ████████████░░░░░░░░ (En Uygun Giriş Noktası)
```

---

## 🏁 Öneri Matrisi

| Önceliğin                  | En İyi Seçim           |
| -------------------------- | ---------------------- |
| Mülakat hazırlığı          | **Message Queue** ⭐    |
| Cloud/DevOps kariyer       | **Container Runtime**  |
| Staff/Principal hedefi     | **Distributed FS**     |
| Storage/CDN kariyer        | **CDN/Object Storage** |
| Edge computing trendi      | **WASM Runtime**       |
| Distributed systems temeli | **Message Queue** ⭐    |

---

## ⭐ Sonuç: Message Queue Neden Öne Çıkıyor?

1. **Evrensel bilgi** — Kafka, RabbitMQ, Pulsar her yerde. Öğrendiğin her yere transfer olur.
2. **Mülakat altın standardı** — Backend mülakatlarında "Kafka nasıl çalışır?" sorusu kaçınılmaz.
3. **Distributed systems kapısı** — Offset, partitioning, replication kavramları Seviye 4 projelere zemin hazırlar.
4. **Microservice mimarisinin kalbi** — Her modern sistemde bir message queue var.
5. **Ekip için uygun** — Broker + Consumer bölünmesi paralel çalışmaya izin veriyor.

> 💡 **Tavsiye**: Message Queue'yu bitirdikten sonra Distributed FS veya CDN/Object Storage'a geçmek çok daha kolay olacak. Temelleri orada öğreniyorsun.

---

## 📚 Her Proje İçin Öğrenme Haritası

---

### 🗂️ 1. Distributed File System (HDFS-like)

#### 🔨 Projede Ne Yapıyorsun?
GFS (Google File System) benzeri, petabyte ölçeğinde çalışabilen dağıtık bir dosya sistemi yazıyorsunuz. Sistem iki ana parçadan oluşuyor:
- **NameNode (kişi 1)**: Tüm dosyaların meta verisini tutar. Hangi dosya, hangi bloklardan oluşuyor, hangi sunucularda? Bunu yönetir. Heartbeat alır, node çöktüğünde replikasyon başlatır.
- **DataNode (kişi 2)**: Gerçek veriyi bloklar halinde depolar. NameNode'a periyodik heartbeat gönderir, blok raporlar. Client'tan doğrudan veri alır/gönderir.
- İkisi birleşince: Bir client "dosya yaz" dediğinde NameNode nereye yazılacağını söyler, client DataNode'lara yazar. 3x replikasyon otomatik sağlanır.

#### 🧠 Temelini Göreceklerin
- Distributed systems nedir, neden zordu
- TCP/IP socket programlama
- RPC (Remote Procedure Call) mekanizması
- Dosya I/O ve blok kavramı

#### 🚀 İleri Düzeyini Göreceklerin
- **Consistent replication** — 3 kopya nasıl senkron tutulur?
- **Fault tolerance** — Bir node çöktüğünde sistem nasıl ayakta kalır?
- **Heartbeat protocol** — Distributed sistemlerde sağlık kontrolü
- **Leader election** — NameNode için single point of failure problemi
- **CAP théorème** — Consistency vs Availability tradeoffları

#### 💼 Hangi İş İlanlarına Uygun?
| İlan Başlığı                     | Neden Uygun?               |
| -------------------------------- | -------------------------- |
| **Distributed Systems Engineer** | Projenin özü               |
| **Storage Engineer**             | Block storage, replication |
| **Platform Engineer**            | Large-scale data infra     |
| **Big Data Engineer**            | HDFS, Hadoop ekosistemi    |
| **Backend Engineer (Senior)**    | Distributed programming    |
| **Site Reliability Engineer**    | Fault tolerance, heartbeat |

---

### 🐳 2. Container Runtime (Docker-like)

#### 🔨 Projede Ne Yapıyorsun?
`docker run ubuntu bash` komutunun arkasında çalışan motoru sıfırdan yazıyorsunuz.
- **Kişi 1 (Runtime/Namespace)**: Linux `clone()` syscall'ı ile yeni process başlatır, PID/NET/MNT/UTS namespace'leri oluşturur. cgroups ile CPU ve memory limiti koyar.
- **Kişi 2 (Image/OverlayFS)**: Container image formatını tasarlar. OverlayFS ile katmanlı dosya sistemi oluşturur (base layer + uygulama layer). Image pull/push mekanizması.
- İkisi birleşince: `boxrun start ubuntu` dediğinde image okunur, namespace oluşturulur, izole process başlar — gerçek container çalışır.

#### 🧠 Temelini Göreceklerin
- Linux process modeli (fork, exec, wait)
- File descriptor, pipe, signal
- Filesystem mount kavramı

#### 🚀 İleri Düzeyini Göreceklerin
- **Linux namespaces** — 7 tür namespace, izolasyon sınırları
- **cgroups v2** — Resource limiting, CPU shares, memory limits
- **OverlayFS / Union mounts** — Copy-on-write layer sistemi
- **OCI specification** — Container endüstri standardı
- **Seccomp / Capabilities** — Container security
- **pivot_root** — Filesystem root değiştirme

#### 💼 Hangi İş İlanlarına Uygun?
| İlan Başlığı                  | Neden Uygun?                       |
| ----------------------------- | ---------------------------------- |
| **Platform / Cloud Engineer** | Container runtime bilgisi          |
| **DevOps Engineer (Senior)**  | Docker/Kubernetes altyapısı anlama |
| **Kubernetes Contributor**    | containerd, runc                   |
| **Security Engineer**         | Container isolation boundaries     |
| **Infrastructure Engineer**   | Linux sistem programlama           |
| **Cloud Native Developer**    | Container-first mimariler          |

---

### 📨 3. Message Queue (Kafka-like) ⭐ ÖNERİLEN

#### 🔨 Projede Ne Yapıyorsun?
Apache Kafka benzeri, yüksek hacimli mesaj kuyruğu sistemi yazıyorsunuz.
- **Kişi 1 (Producer + Broker)**: Topic ve partition yapısını tasarlar. Append-only log dosyasına mesajları yazar. Producer'dan gelen mesajları alır, doğru partition'a yönlendirir. Offset index oluşturur.
- **Kişi 2 (Consumer + Consumer Groups)**: Consumer'ın offset'ini takip eder. Consumer group'ları yönetir — hangi consumer hangi partition'ı okusun? Rebalancing yapar.
- İkisi birleşince: Producer mesaj gönderir → Broker'a gelir → Log dosyasına yazılır → Consumer kendi offset'inden itibaren okur → Consumer group load-balance eder.

#### 🧠 Temelini Göreceklerin
- Pub/Sub pattern ve event-driven mimari
- TCP socket programlama
- Dosya I/O (append-only, binary format)
- Network protocol tasarımı

#### 🚀 İleri Düzeyini Göreceklerin
- **Log-based storage** — Kafka'nın temel yeniliği: immutable, append-only log
- **Offset tracking** — At-least-once, at-most-once, exactly-once semantics
- **Consumer groups** — Distributed consume, load balancing
- **Partitioning stratejileri** — Key-based, round-robin
- **Log compaction** — Eski mesajları temizleme
- **Backpressure** — Hızlı producer, yavaş consumer problemi
- **Replication** (bonus) — Leader/follower partition replication

#### 💼 Hangi İş İlanlarına Uygun?
| İlan Başlığı                      | Neden Uygun?                     |
| --------------------------------- | -------------------------------- |
| **Backend Engineer (Mid/Senior)** | Event-driven sistemler her yerde |
| **Distributed Systems Engineer**  | Pub/sub, offset, replication     |
| **Data Engineer**                 | Kafka pipeline kurma/anlama      |
| **Platform Engineer**             | Messaging infrastructure         |
| **Fintech Engineer**              | Event sourcing, transaction log  |
| **Microservices Architect**       | Service-to-service iletişim      |
| **Site Reliability Engineer**     | Kafka ops, monitoring            |

---

### 🌍 4. CDN / Object Storage (S3+CloudFront-like)

#### 🔨 Projede Ne Yapıyorsun?
AWS S3 + Cloudflare CDN benzeri, içerikleri coğrafi olarak dağıtan sistem yazıyorsunuz.
- **Kişi 1 (Edge Cache)**: İstanbul, Berlin, Tokyo gibi edge nodelar yazar. LRU cache yönetir. Cache hit → hemen döner. Cache miss → origin'e gider, cache'ler, döner. TTL ve ETag yönetir.
- **Kişi 2 (Origin/Object Storage)**: S3 benzeri REST API yazar (PUT/GET/DELETE/LIST). Consistent hashing ile objeyi doğru node'a yönlendirir. Multi-node storage cluster yönetir.
- İkisi birleşince: User bir dosya ister → en yakın edge node'a gider → cache varsa hızlı döner → yoksa origin'den çeker → hem cache'ler hem döner.

#### 🧠 Temelini Göreceklerin
- HTTP server yazımı (REST API)
- Cache kavramı (hit, miss, eviction)
- Hash fonksiyonu ve ring kavramı

#### 🚀 İleri Düzeyini Göreceklerin
- **Consistent hashing** — Node ekle/çıkar, minimum redistribution
- **Cache invalidation** — CS'in en zor problemi, gerçekten
- **Geo-distribution** — Latency optimizasyonu, PoP kavramı
- **S3 API compatibility** — Industry standard protocol
- **Multi-region replication** — Eventual consistency
- **ETag / Conditional requests** — Bandwidth optimizasyonu

#### 💼 Hangi İş İlanlarına Uygun?
| İlan Başlığı                      | Neden Uygun?                    |
| --------------------------------- | ------------------------------- |
| **Storage Engineer**              | Object storage, S3 uyumu        |
| **Cloud Infrastructure Engineer** | AWS/GCP/Azure benzeri sistemler |
| **CDN Engineer**                  | Edge computing, caching         |
| **Backend Engineer (Senior)**     | Distributed caching, API design |
| **Performance Engineer**          | Latency optimization            |
| **Solutions Architect**           | Cloud storage mimarisi          |

---

### 🔧 5. WebAssembly Runtime

#### 🔨 Projede Ne Yapıyorsun?
.wasm binary dosyalarını çalıştıran bir WebAssembly runtime yazıyorsunuz.
- **Kişi 1 (Binary Parser)**: .wasm formatını parse eder. Magic bytes, version header, section'ları ayrıştırır: Type section, Import section, Function section, Export section. Her fonksiyonun type signature'ını çıkarır.
- **Kişi 2 (Stack Machine + Executor)**: Parse edilen instruction'ları çalıştırır. Stack-based VM: push/pop ile işlemler yapılır. Memory operasyonları, function call/return, branching.
- İkisi birleşince: `wasmrt hello.wasm` dediğinde binary parse edilir → validate edilir → stack machine çalıştırır → WASI üzerinden sistem kaynaklarına erişir.

#### 🧠 Temelini Göreceklerin
- Binary dosya formatları ve parsing
- Stack veri yapısı ve işlemleri
- Type system kavramı

#### 🚀 İleri Düzeyini Göreceklerin
- **Stack machine architecture** — Register-based vs stack-based VM farkı
- **LEB128 encoding** — WASM'ın number encoding formatı
- **Type validation** — Statik analiz, stack depth kontrolü
- **WASI (WebAssembly System Interface)** — Portable sistem arayüzü
- **Sandboxing** — Güvenli izole çalıştırma
- **AOT/JIT compilation** (bonus) — Interpreter'dan native koda geçiş

#### 💼 Hangi İş İlanlarına Uygun?
| İlan Başlığı                | Neden Uygun?                        |
| --------------------------- | ----------------------------------- |
| **Runtime Engineer**        | VM ve runtime geliştirme            |
| **Compiler Engineer**       | Binary format, code generation      |
| **Edge Computing Engineer** | Cloudflare Workers, WASM serverless |
| **Security Engineer**       | Sandbox, isolation                  |
| **Toolchain Engineer**      | Build tools, cross-compilation      |
| **Embedded Systems**        | Lightweight runtime                 |

---

## 🧩 Tüm Projelerde Ortak Kazanımlar

Her proje hangisi olursa olsun bunları öğretir:

- ✅ **Protokol tasarımı** — Client-server iletişimi nasıl kurulur?
- ✅ **Binary/text format parse etme** — Gerçek data nasıl okunur?
- ✅ **Error handling** — Distributed sistemlerde her şey patlayabilir
- ✅ **Benchmarking** — Performansı nasıl ölçersin?
- ✅ **Concurrency** — Thread, mutex, lock-free veri yapıları
- ✅ **Testing** — Unit + integration test, chaos testing
- ✅ **Code review alışkanlığı** — 2 kişilik ekip, doğal code review

---

## 🔍 Proje Analizleri: Kapsam, Teknoloji ve Analojiler

Bu bölümde projelerin hangi alana düştüğünü, kullanılacak teknolojileri, gerçek hayattaki örneklerini ve 7 yaşında bir çocuğa anlatırmış gibi basitleştirilmiş açıklamalarını bulabilirsin.

### 1. Distributed FS (Dağıtık Dosya Sistemi)
- **Alan:** Backend / Infrastructure / Systems Programming
- **Kullanılacak Teknolojiler/Diller:** **Go**, **Rust**, C++ veya Java (Hadoop Java ile yazılmıştır ancak modern altyapılar Go/Rust ile yazılır). Ağ iletişimi (gRPC, TCP), Dosya Okuma/Yazma (Storage I/O).
- **Özet ve Gerçek Hayat Örnekleri:** Çok büyük veri kümelerini tek bir sunucuya sığdıramadığımızda, bunları parçalara bölerek onlarca/yüzlerce sunucuya dağıtan ve donanım arızası durumunda veri kaybını önleyen bir depolama kümesidir. **Gerçek hayat örneği:** Google Drive altyapısı (Colossus), Hadoop HDFS. Biz burada veriyi parçalayıp farklı sunuculara (DataNode) dağıtan ve nerede olduklarını haritalayan (NameNode) bir bulut depolama çekirdeğinin prototipini yapıyoruz.
- **Örnek-2:** Mesela Google Drive veya iCloud'a 100 GB'lık dev bir video yüklediğini düşün. Bu video gidip tek bir bilgisayara sığdırılmaz; çok küçük parçalara bölünerek dünyanın farklı yerlerindeki binlerce sunucuya dağıtılır. Sunuculardan biri yansa bile diğerlerindeki yedek parçalardan video anında geri birleştirilir. Amaç, devasa boyuttaki dosyaları sınırsızmış gibi saklamak ve ne olursa olsun asla kaybetmemektir. Biz burada veriyi parçalayıp farklı sunuculara dağıtan bir bulut depolama çekirdeğinin prototipini yapıyoruz.
- **👶 7 Yaşında Çocuğa Anlatım:** "Diyelim ki elinde devasa bir Lego şatosu var ve senin odandaki tek bir kutuya asla sığmıyor. Biz bu dev şatoyu parçalara bölüyoruz ve senin 3 farklı arkadaşının odasında saklıyoruz. Arkadaşlarından biri hastalanıp dışarı çıkamasa bile diğer iki arkadaşında şatonun birer kopyası olduğu için hiçbir şey kaybolmuyor ve oynamaya devam edebiliyorsun."
- **Akış (Flowchart):**
  ```mermaid
  graph LR
      Client[Kullanıcı] -->|Dosya Yükle| NN[NameNode<br/>Yönetici]
      NN -->|Şu sunuculara yaz| Client
      Client -->|Parça 1| DN1[DataNode 1]
      Client -->|Parça 2| DN2[DataNode 2]
      DN1 -.->|Arıza için Yedek| DN3[DataNode 3]
  ```

### 2. Container Runtime
- **Alan:** Systems Programming / DevOps / Cloud Infrastructure
- **Kullanılacak Teknolojiler/Diller:** **Go** (sektör standardı), **Rust** veya C. Linux İşletim Sistemi Çağrıları (Linux API: namespaces, cgroups, chroot/pivot_root).
- **Özet ve Gerçek Hayat Örnekleri:** Bir uygulamanın her işletim sisteminde veya sunucuda, sistemi kirletmeden, diğer programlardan izole ve güvenli bir şekilde çalışmasını sağlayan motordur. **Gerçek hayat örneği:** Docker, Kubernetes'in altındaki containerd veya runc. Biz burada Docker'ın uygulamaları çalıştıran arka plan motorunun (backend'inin) minyatür bir prototipini yapıyoruz.
- **Örnek-2** Oynamak istediğin çok eski bir oyunu yeni bilgisayarına kurmaya çalıştığını düşün; "dll eksik", "Windows sürümü desteklenmiyor" gibi tonla hata verir. Container sistemi ise bu oyunları, çalışması için gereken her şeyle (işletim sistemi, ayarlar, dosyalar) bir kutuya hapseder ve "al bunu hangi bilgisayarda açarsan aç, kesin çalışır" der. Amaç, bir programı bilgisayara hiçbir şey kurmadan, sistemi de kirletmeden her yerde %100 aynı şekilde (izole) çalıştırmaktır. Biz burada bu sistemi (Docker) işleten arka plan motorunun minyatür bir prototipini yapıyoruz.
- **👶 7 Yaşında Çocuğa Anlatım:** "Bir balığı yaşatmak istiyorsun ama dışarıdaki gölün suyu balığa hiç uygun değil. Biz balık için; içine tam ona uygun suyu, yemi ve oksijeni koyduğumuz, dışarısı ile bağlantısı tamamen kesilmiş şeffaf bir kavanoz yapıyoruz. Bu kavanozu ister salona götür ister bahçeye, balık her yerde aynı şekilde mutlu yaşar. Biz uygulamalar için o kavanozu tasarlıyoruz."
- **Akış (Flowchart):**
  ```mermaid
  graph TD
      User[Başlat Komutu] --> Runtime[Container Runtime]
      Runtime --> NS[Namespaces<br/>İzolasyon Duvarları]
      Runtime --> CG[Cgroups<br/>Hafıza/CPU Sınırı]
      Runtime --> FS[OverlayFS<br/>Sanal Dosya Sistemi]
      NS & CG & FS --> Process[İzole Uygulama Çalışır]
  ```

### 3. Message Queue ⭐
- **Alan:** Backend / Distributed Systems / Data Engineering / Microservices
- **Kullanılacak Teknolojiler/Diller:** **Java / Kotlin** (Kafka, Pulsar genelde JVM üzerindedir), **Go** veya **Rust**. TCP sockets, Diske sırayla yazma (Append-only logs), Concurrency (Multithreading/Goroutines).
- **Özet ve Gerçek Hayat Örnekleri:** Servisler veya uygulamalar arası güvenli ve asenkron (eşzamanlı olmayan) mesaj taşıyıcısıdır. Uygulamalardan biri çok hızlı veri üretip diğeri yavaş tüketiyorsa, verilerin kaybolmadan sıraya girmesini sağlar. **Gerçek hayat örneği:** Apache Kafka, RabbitMQ, Amazon SQS. Biz burada modern mikroservislerin birbiriyle haberleşmesini sağlayan, çökmeyen dev bir dijital postane sisteminin prototipini yapıyoruz.
- **Örnek-2:** İnternetten alışveriş yaptığında telefona hemen "siparişiniz alındı" mesajı gelir. Halbuki indirim gününde aynı saniyede 1 milyon kişi satın al tuşuna basmıştır. Sistem 1 milyon banka işlemini aynı anda yapsa anında çöker. Bunun yerine siparişler bu "kuyruk" sistemine (Message Queue) atılır ve sana siparişi aldık denilir. Arka planda ise sistem hiç yorulmadan, sırası geldikçe kuyruktaki siparişleri tek tek çeker, parayı keser ve kargoya verir. Para çekilemezse hata döndürür. Amaç; gelen milyonlarca isteği anında sıraya koymak ve sitenin çökmesini engellemektir. Biz burada bu dev postane sisteminin prototipini yapıyoruz.
- **👶 7 Yaşında Çocuğa Anlatım:** "Diyelim ki sen çok hızlı resim çiziyorsun ama arkadaşın senin çizdiğin bu boyama kitaplarını boyarken çok yavaş, sana yetişemiyor. Sen çizdiklerini doğrudan onun eline vermek yerine yan masaya sırayla üst üste koyuyorsun. Arkadaşın işini bitirdikçe masadan sıradakini alıp boyuyor. Böylece kimse birbirini beklemek zorunda kalmıyor ve çizilen resimler atılmıyor. Biz işte o sıraya koyma masasını inşa ediyoruz."
- **Akış (Flowchart):**
  ```mermaid
  graph LR
      P[Uygulama 1<br/>Producer / Üretici] -->|Yeni Mesaj| B[Broker<br/>Mesaj Kuyruğu]
      B -->|Sırası gelince| C1[Uygulama 2<br/>Consumer 1]
      B -->|Sırası gelince| C2[Uygulama 3<br/>Consumer 2]
  ```

### 4. CDN / Object Storage
- **Alan:** Backend / Infrastructure / Edge Computing
- **Kullanılacak Teknolojiler/Diller:** **Go**, **Rust**, C++. İletişim için HTTP/REST API mimarisi, Önbellekleme (Caching/LRU), Veri arama algoritmaları (Consistent Hashing), Veritabanı entegrasyonu.
- **Özet ve Gerçek Hayat Örnekleri:** Resim, video gibi statik dosyaları coğrafi olarak dünyadaki birçok sunucuya dağıtan, böylece bir kullanıcının o içeriğe nerede olursa olsun en hızlı şekilde erişmesini sağlayan sistemdir. **Gerçek hayat örneği:** AWS S3 (depolama kısmı), Cloudflare veya Akamai (dağıtım/CDN kısmı). Biz burada bulut depolama sistemi ile bu dosyalara en yakın istasyondan şimşek hızında ulaştıran dağıtım ağının prototipini kuruyoruz.
- **Örnek-2:** Türkiye'den Amerika merkezli bir Netflix dizisi açtığında, o video aslında taa Amerika'dan gelmez; gelseydi çok fazla donardı. CDN sistemi, o videoyu Amerika'dan alır ve önceden İstanbul'daki kendi sunucusuna kopyalar. Sen videoyu açtığında video sana kendi ülkenden, en yakın yerden şimşek hızında gelir. Amaç dünyanın neresinde olursan ol, büyük içeriklerin sana en düşük gecikmeyle (lag olmadan) anında ulaştırılmasıdır. Biz burada bu şimşek hızlı video dağıtım ağının prototipini yapıyoruz.
- **👶 7 Yaşında Çocuğa Anlatım:** "Düşün ki en sevdiğin dondurmanın fabrikası dünyanın öbür ucunda, Amerika'da. Dondurma sipariş etsen gelmesi aylar sürer, erir gider. Ama bu dondurmacı senin sokağındaki bakkala da küçük, akıllı bir dondurma dolabı koyuyor. Artık canın çektiğinde Amerika'dan sipariş etmek yerine koşup bakkaldan hepi topu 5 dakikada alabiliyorsun. Biz o süper hızlı dondurma dolaplarını yönetecek sistemi tasarlıyoruz."
- **Akış (Flowchart):**
  ```mermaid
  graph TD
      User[Kullanıcı Dosya İster] --> Edge[Türkiye'deki Edge Sunucu]
      Edge -- "Dosya yoksa (Cache Miss)" --> Origin[Amerika'daki Ana Sunucu / S3]
      Origin -->|Dosyayı Gönder| Edge
      Edge -->|1- Kullanıcıya Ver<br>2- Diğerleri İçin Sakla| User
  ```

### 5. WebAssembly (WASM) Runtime
- **Alan:** Systems Programming / Compilers / VM Design / Edge Computing
- **Kullanılacak Teknolojiler/Diller:** **Rust** (wasmer/wasmtime gibi çok popüler çözümler Rust'tadır), C++ veya Zig. İkili dosya okuma (Binary Parsing), Stack tabanlı Sanal Makine (Stack-based Virtual Machine) tasarımı.
- **Özet ve Gerçek Hayat Örnekleri:** Herhangi bir programlama dilinde (C++, Rust vb.) kodlanmış yazılımı çok hafif, güvenli ve "her yerde çalışabilen" yalıtılmış bir ikili dosya (binary) formatına çevirip bunu işleten yapıdır. **Gerçek hayat örneği:** Figma'nın internet tarayıcısında masaüstü programı gibi aşırı hızlı çalışması, Cloudflare Workers. Biz, kodları baytlara indirgenmiş halinden satır satır okuyup makinenin kendi dilinde işleten bir "çalıştırma beyni" prototipi yapıyoruz.
- **Örnekl-2:** Eskiden internet tarayıcısında (Chrome/Safari) sadece basit web siteleri açılabilirdi; Photoshop gibi ağır masaüstü programları kasardı. WebAssembly sayesinde bilgisayarına hiçbir şey kurmana gerek kalmadan, o devasa ağır masaüstü programlarını direkt web sitesi üzerinden aynı masaüstü hızında ve akıcılığında kullanabilirsin (Figma gibi). Amaç hangi dilde yazılırsa yazılsın, dev bir programı sıfır kurulumla her cihazda tarayıcı üzerinden fişek gibi çalıştırmaktır. Biz işte bu ağır kodları bilgisayarın anlayacağı hıza çeviren bir beynin prototipini yapıyoruz.
- **👶 7 Yaşında Çocuğa Anlatım:** "Dünyadaki herkesin farklı diller tartıştığını düşün (Çince, İspanyolca, Fransızca). Kimse birbirini anlamıyor. Biz öyle sihirli bir müzik kutusu cebimizde taşıyoruz ki; içine hangi şarkıyı atarsan at, o kutu anında müziği tüm insanların anladığı tek ve evrensel ortak bir melodiye dönüştürüyor ve şimşek hızında sana dinletiyor."
- **Akış (Flowchart):**
  ```mermaid
  graph LR
      App[C++/Rust Kodu] -.->|Derleyici| WASM[Evrensel .wasm Dosyası]
      WASM --> Parser[WASM Runtime<br/>Ayrıştırıcı/Parser]
      Parser --> Stack[Sanal Makine<br/>İşlemci / Executor]
      Stack --> Result[Sonuç / Ekrana Çıktı]
  ```

---

## 📈 İlerleme ve Çalışma Planı (Ne Yapmalıyız?)

Hangi projeyi ve teknoloji yığınını seçeceğinize karar vermek için aşağıdaki adımları (Plan) izleyin:

### 1- Ortak İlgi Alanına Göre Seçim Yapın (Karar Aşaması)
Kariyer hedeflerinize ve öğrenmek istediğiniz şeylere göre karar vermelisiniz (Örn: Hepimiz Java, Spring vs. biliyoruz, o halde Backend yeteneklerimizi taçlandırmak için **Message Queue** çok mantıklı olabilir).
- Eğer **Backend Mühendisi / Yazılım Mühendisi** pozisyonu hedefleniyorsa → **Message Queue** (Kuşkusuz 1 numara) veya CDN.
- Eğer **DevOps / Platform Engineer** hedefleniyorsa → **Container Runtime**.
- Eğer **Çok Düşük Seviye (Low-level) Sistem / Derleyici** hedefleniyorsa → **WASM Runtime** veya Distributed FS.

### 2- Dil Seçimine Karar Verin
Projelere Göre Teknolojiler:
- **Message Queue** için: **Java / Kotlin** çok iyi uyar (Kafka mantığı). Modern yaklaşımla **Go** veya **Rust** çok havalı durur.
- **Distributed FS / CDN** için: Kesinlikle **Go**. (Dağıtık sistemlerin şu anki kralları hep Go kullanır).
- **Container Runtime** için: Tartışmasız **Go** (Docker, K8S ekosistemi).
- **WASM Runtime** için: **Rust** (Bu konudaki kütüphane ve endüstri standardıdır).

### 3- Sprint (Aşama) Planlaması
Karar verildikten sonra şu şekilde bir rota oluşturmalısınız:

1. **Öğrenme ve Tasarım (Araştırma - 1. Hafta):**
   - Seçtiğiniz sistemin ana kaynağını okuyun (Örn: Kafka belgesini okumak).
   - "Hangi dil/framework ile yazacağız?" netleştirin ve GitHub deposunu (Repo) oluşturun.
2. **"Çalışan En Basit Şey" Prototipi (MVP - 2. ve 3. Hafta):**
   - Birbirine veri yollayabilen sadece tek dosyalık basit bir kod ve basit TCP Socket'leri ayağa kaldırın. Henüz 2 kişi bölüşmeyin, beraber pair-programming yapıp olayı kavrayın.
3. **Modüllere Bölünme (Uygulama - 4. Hafta ve sonrası):**
   - Sistemi iki ana yapıtaşına ayırın.
   - Örnek MQ: Biri mesajları sıraya yazan(Producer/Broker Log), diğeri sıradan okumak isteyenleri yöneten(Consumer/Offset) kodları yazsın.
4. **Kalite Artırımı (Test & Dökümantasyon):**
   - Birbirinizin kodunu GitHub Merge Request atarken gözden geçirin (Code Review alışkanlığı edinilir).
   - Testleri yazıp sınırları zorlayın (Sunucuyu fişten çekip verilerin uçup uçmadığına bakın).

Bu tabloyu ikiniz de inceleyip nihai kararınızı verdikten sonra, o projeye has detaylı görev dağılımlarına geçebiliriz. Hangi projede karar kıldığınızda söyleyin, ona göre klasör yapısı ve kod tasarımını başlatalım.

---

*Oluşturulma: 2026-02-25 / Güncelleme: 2026-03-01*
