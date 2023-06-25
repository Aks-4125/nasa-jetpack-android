 <h1 align="center"> Android NASA -  Jetpack Compose</h1>

<!-- Badges -->
<p align="center">
  <img src="https://img.shields.io/badge/Language-Kotlin-orange">
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen">
  <img src="https://img.shields.io/badge/Architecture-MVVM-blueviolet">
</p>

## Project Structure

The project follows a multimodule architecture with the following modules:

- **Core Module**: Contains common UI components and utilities that can be shared across different modules.
- **Main Module**: Contains feature-specific implementations and functionalities.

---

## Code Quality and Continuous Integration

- **Code Style**: The code in this repository adheres to the [ktlint](https://ktlint.github.io/) code style.
- **Code Scanning**: The project includes code scanning with ktlint & Android Code Inspector to ensure consistent code formatting and style.
- **Continuous Integration**: The project is integrated with CircleCI for automated build, testing, and code quality checks.

---

## Features
- **Dynamic Dark Mode UI**: The project supports dynamic dark mode UI for mobile and tablet devices. It adjusts the app's theme based on the system's dark mode setting, providing a consistent user experience across different lighting conditions.

- **Responsive Design**: The UI is optimized for different device orientations, including vertical and horizontal layouts. It ensures a seamless user interface regardless of the device's orientation.

---

# Android Libraries

This repository contains an Android project that demonstrates the usage of various libraries in Android app development. The project is written in Kotlin and showcases different functionalities and integrations using these libraries.

## Libraries Used

The project utilizes the following libraries:

- **Core Libraries**
  - [AndroidX Core-KTX](https://developer.android.com/jetpack/androidx/releases/core): Provides core utilities and extensions for AndroidX libraries.

- **Jetpack Compose**
  - [Compose UI](https://developer.android.com/jetpack/compose): A modern toolkit for building native Android UI.
  - [Compose Graphics](https://developer.android.com/jetpack/compose/graphics): Provides graphics and animation capabilities for Compose UI.
  - [Compose Tooling Preview](https://developer.android.com/jetpack/compose/tooling): Tooling support for Compose UI development.
  - [Material3](https://developer.android.com/jetpack/compose/material3): Material Design components and theming for Compose UI.
  - [Activity Compose](https://developer.android.com/jetpack/compose/activity): Integration of Compose UI with the Android activity lifecycle.

- **Dependency Injection**
  - [Dagger Hilt](https://dagger.dev/hilt): A compile-time dependency injection framework for Android.

- **Networking**
  - [Retrofit](https://square.github.io/retrofit): A type-safe HTTP client for Android and Java.
  - [OkHttp](https://square.github.io/okhttp): A powerful HTTP client for Android and Java.

- **JSON Deserialization**
  - [Moshi](https://github.com/square/moshi): A modern JSON library for Kotlin and Java.

- **Image Loading**
  - [Coil](https://coil-kt.github.io/coil): An image loading library for Android apps.

- **Navigation**
  - [Android Navigation Component](https://developer.android.com/guide/navigation): A component that helps with navigation and deep linking in Android apps.

- **Memory Leak Detection**
  - [LeakCanary](https://square.github.io/leakcanary): A memory leak detection library for Android.

- **Splash Screen**
  - [Core Splash Screen](https://developer.android.com/jetpack/androidx/releases/core-splashscreen): Provides a simplified way to create splash screens.

- **Coroutines**
  - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines): Asynchronous programming library for Kotlin.

- **Paging**
  - [AndroidX Paging](https://developer.android.com/topic/libraries/architecture/paging): Helps with paginated data loading in RecyclerViews.

- **Testing**
  - [MockK](https://mockk.io): A mocking library for Kotlin.
  - [AndroidX Test](https://developer.android.com/training/testing): Provides testing support for Android apps.
  - [JUnit Jupiter](https://junit.org/junit5): A programming model and testing framework for Java applications.
  - [Arch Core Testing](https://developer.android.com/topic/libraries/architecture/testing): Provides testing utilities for Android Architecture Components.

- **Room**
  - [AndroidX Room](https://developer.android.com/topic/libraries/architecture/room): Provides an abstraction layer over SQLite for database operations.
 
---

## Screenshots

 | Dark Mode  | Tablet |
| ------------- | ------------- |
| ![](https://github.com/Aks-4125/nasa-jetpack-android/blob/master/screenshots/darkmode.gif) | ![](https://github.com/Aks-4125/nasa-jetpack-android/blob/master/screenshots/tablet.gif)|

---

## Getting Started
To get started with the project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the project on your Android device or emulator.

---

## Download APK directly from <a href="https://github.com/Aks-4125/nasa-jetpack-android/blob/master/screenshots/nasa-debug.apk" download>Here</a>

## Download Zip directly from <a href="https://github.com/Aks-4125/nasa-jetpack-android/blob/master/screenshots/Nasa%20Jetpack.zip" download>Here</a>


License
The project is licensed under the [MIT License](https://github.com/Aks-4125/nasa-jetpack-android/blob/master/LICENSE.md).
