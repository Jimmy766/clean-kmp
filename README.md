# Shop Stores app - Kotlin Multiplatform

It demonstrates a simple mobile app that loads and displays shop/store data using shared business logic across Android and iOS.

## 🚀 Features

- Shared Kotlin Multiplatform architecture
- Room database integration (Android)
- Jetpack Compose UI
- Flow-based reactive data layer
- Use Cases and Repository pattern
- Dependency Injection with Koin
- Coroutines and kotlinx.serialization
- Unit tests with fake repository

## 🧱 Architecture Overview

This project follows a layered architecture inspired by Clean Architecture principles, structured inside the shared `Kotlin Multiplatform` module:

```
shared/
└── src/
    └── commonMain/
        └── com.bimm.takehomeassignmnent/
            ├── domain/
            │   ├── models/                 # Pure business models (shared across platforms)
            │   ├── repository/             # Abstract contracts (interfaces)
            │   └── usecase/                # Application-specific business logic
            └── data/
                ├── repository/             # Repository implementations (depends on dataSource)
                └── dataSource/
                    ├── room/               # Local DB (Room - Android)
                    └── json/               # JSON loading utilities (KMP-compliant)
```

### 🔄 Flow of Data

1. **UI Layer (Android/iOS)** calls →  
2. **UseCase Layer** uses →  
3. **ShopStoresRepository (interface)** implemented by →  
4. **ShopStoreRepositoryImpl**, which coordinates →  
5. **Room / JSON data sources**

---

## 🛠️ Tech Stack

| Layer              | Technology                                    |
|--------------------|-----------------------------------------------|
| UI (Android)       | Jetpack Compose                               |
| UI (iOS)           | Swift UI (via KMP integration - expected)     |
| Shared Logic       | Kotlin Multiplatform (KMP)                    |
| Data Storage       | Room (Android)                                |
| DI                 | Koin                                          |
| Async / Reactive   | Kotlin Coroutines + Flow                      |
| Serialization      | kotlinx.serialization                         |
| Testing            | JUnit 4, Kotlin Test, kotlinx.coroutines.test |

## 🧪 Testing

Unit tests are written using `kotlin.test` and run in the `commonTest` and `androidTest` source sets.

To run all tests:

```bash
./gradlew allTests
```

To run Android instrumented tests:

```bash
./gradlew connectedAndroidTest
```

## 📄 How to Run

### Android
1. Open project in Android Studio.
2. Run `composeApp` module on an emulator or device.

### iOS
> WIP – iOS target is built using KMP frameworks. Swift integration planned or in progress.

## 📦 Requirements

- Android Studio Hedgehog or later
- Kotlin 1.9.x
- Gradle 8.x
- Xcode 15+ (for iOS builds)

Deep Wiki [![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/Jimmy766/clean-kmp)

## 🙋 Author

**Jaison Villarroel**  
Full-stack & Mobile Developer  
