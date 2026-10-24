# prayer_app

[![CI](https://github.com/MrIsrak/prayer_app/actions/workflows/ci.yml/badge.svg)](https://github.com/MrIsrak/prayer_app/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Android app (Java) for daily Jewish prayer: prayer times (zmanim) for your location,
the Hebrew date, a weekly timetable, Tehillim and blessings, with Firebase login.

This was my first larger Android project (2024–2026). A rewrite with a layered
architecture (use cases, repositories, ViewModels) and unit tests is in progress.

## Features

- Daily zmanim calculated on-device with [KosherJava Zmanim](https://github.com/KosherJava/zmanim)
  from the device location.
- Weekly timetable with day-by-day navigation.
- Tehillim and blessings screens.
- Firebase Authentication (Google sign-in) and Firestore.
- Light and dark themes; English, Russian and Hebrew (RTL) UI; tablet layouts.

## Tech stack

Java 8 · Android Views + Material Components · Navigation · Firebase Auth / Firestore /
Analytics · KosherJava Zmanim · Glide · GitHub Actions

## Build

1. Open the project in Android Studio, or use JDK 17 on the command line.
2. To use your own Firebase project, replace `app/google-services.json` with the file
   from the Firebase console (Project settings → Your apps).
3. Build and run:

   ```bash
   ./gradlew assembleDebug
   ```

## Credentials

OAuth client secrets, keystores and `.env` files are git-ignored and must never be committed.
Keep local copies outside the repo.

## License

[MIT](LICENSE)
