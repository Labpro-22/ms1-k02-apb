# if3210-ms1-tubes-2026

Family Location Sharing Application for Android

## Tech Stack

- **Language**: Kotlin
- **Min SDK**: Android 11 (API Level 30)
- **Target SDK**: Android 15 (API Level 35)
- **Architecture**: MVVM with Clean Architecture
- **UI**: Android XML Views + Jetpack Navigation
- **Networking**: Retrofit + OkHttp
- **Local Database**: Room Database (for pinned families)
- **Dependency Injection**: Manual (App-level singletons)
- **Image Loading**: Coil
- **Coroutines**: Kotlin Coroutines + Flow

## Library Dependencies

### Core Android
- `androidx.core:core-ktx:1.13.1`
- `androidx.appcompat:appcompat:1.7.0`
- `com.google.android.material:material:1.12.0`
- `androidx.constraintlayout:constraintlayout:2.1.4`

### Navigation
- `androidx.navigation:navigation-fragment-ktx:2.8.3`
- `androidx.navigation:navigation-ui-ktx:2.8.3`

### Lifecycle
- `androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6`
- `androidx.lifecycle:lifecycle-livedata-ktx:2.8.6`
- `androidx.lifecycle:lifecycle-runtime-ktx:2.8.6`

### Room Database
- `androidx.room:room-runtime:2.6.1`
- `androidx.room:room-ktx:2.6.1`

### Networking
- `com.squareup.retrofit2:retrofit:2.11.0`
- `com.squareup.retrofit2:converter-gson:2.11.0`
- `com.squareup.okhttp3:okhttp:4.12.0`
- `com.squareup.okhttp3:logging-interceptor:4.12.0`

### Security
- `androidx.security:security-crypto:1.1.0-alpha06`

### Image Loading
- `io.coil-kt:coil:2.7.0`

### Coroutines
- `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1`
- `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1`

## Setup Instructions

### Prerequisites
1. **Android Studio**: Hedgehog (2023.1.1) or later
2. **JDK**: 17 or later
3. **Android SDK**: API Level 30-35

### First-Time Setup
```bash
# Create local.properties with your Android SDK path
echo "sdk.dir=C:\\Users\\Acer\\AppData\\Local\\Android\\Sdk" > local.properties
# Or on macOS/Linux:
echo "sdk.dir=/Users/username/Library/Android/sdk" > local.properties
```

### Building the Project
```bash
./gradlew assembleDebug
```

### Running the App
```bash
./gradlew installDebug
```