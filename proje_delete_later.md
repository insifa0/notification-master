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

*Oluşturulma: 2026-02-25*
