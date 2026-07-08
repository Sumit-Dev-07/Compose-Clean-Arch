# Compose Clean Architecture

A modern Android project demonstrating **Clean Architecture** principles using **Jetpack Compose**, **Hilt**, **Retrofit**, and **Coroutines Flow**.

## 🚀 Features

- **Clean Architecture**: Separation of concerns into Data, Domain, and UI layers.
- **Modern UI**: Built entirely with Jetpack Compose.
- **Dependency Injection**: Powered by Hilt for a scalable and testable codebase.
- **Reactive Programming**: Uses Kotlin Coroutines and Flow for asynchronous data handling.
- **Networking**: Retrofit & OkHttp for API communication with a robust `ApiState` wrapper.
- **Material 3**: Utilizing the latest Material Design components and theming.

## 🏗️ Architecture

The project follows the Clean Architecture pattern:

- **Data Layer**: Contains API implementations (`Retrofit`), Data Sources, and Repository implementations.
- **Domain Layer**: The core logic of the app, containing Use Cases and Repository interfaces. This layer is independent of any other layer.
- **UI (Presentation) Layer**: Jetpack Compose screens, ViewModels, and Navigation.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson)
- **Asynchronous**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- **Architecture**: Clean Architecture + MVVM

## 📁 Project Structure

```text
app/src/main/java/com/app/compose/
├── data/           # Remote & Local data sources, Repository implementations
├── domain/         # Business logic: UseCases & Repository interfaces
├── ui/             # UI Components, Screens (Login, Home, Splash), ViewModels
├── di/             # Hilt Modules
└── util/           # Utility classes (ApiState, Constants, etc.)
```

## 🛠️ Getting Started

1. Clone the repository.
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device.

## 📝 License

```text
Copyright 2024 Compose Clean Arch.
```
