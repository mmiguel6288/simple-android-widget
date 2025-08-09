# Simple Widget App

A native Android application that demonstrates a home screen widget implementation.

## Features

- Interactive counter widget for Android home screen
- Increment, decrement, and reset functionality
- Persistent counter state across device reboots
- Clean, modern Material Design UI

## Widget Features

- **Counter Display**: Shows current count value
- **Increment Button (+)**: Increases counter by 1
- **Decrement Button (-)**: Decreases counter by 1  
- **Reset Button**: Resets counter to 0

## Building the App

### Prerequisites

- JDK 11 or higher
- Android SDK (API level 21+)
- Git

### Local Build

```bash
cd SimpleWidgetApp
./gradlew assembleDebug
```

The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`

### GitHub Actions

This repository includes a GitHub Actions workflow that automatically builds the APK on every push to the main branch. The built APK is available as a downloadable artifact.

## Installation

1. Enable "Install from unknown sources" in Android settings
2. Install the APK file
3. Long press on your home screen
4. Tap "Widgets"
5. Find "Simple Widget App" and drag the "Counter Widget" to your home screen

## Technical Details

- **Min SDK**: Android 5.0 (API level 21)
- **Target SDK**: Android 14 (API level 34)
- **Language**: Kotlin
- **Architecture**: Native Android App Widget

## License

Apache License 2.0