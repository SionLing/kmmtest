# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a mobile application development project for a Picture Card Gallery App. The project is currently in the requirements and planning phase, with documentation outlining the desired features and architecture.

## Project Structure

- `PictureCardGalleryRequirements.md` - Main requirements document detailing app features, user flow, and technical requirements
- `PictureCardGalleryRequirements.puml` - PlantUML diagram showing the app's feature overview and user flow

## Target Platform & Technology

- **Platform**: Android
- **Language**: Kotlin (recommended)
- **UI Components**: RecyclerView for grid layout, CardView for picture cards
- **Navigation**: Jetpack Navigation or similar
- **Image Loading**: Glide or Coil library
- **Architecture**: Standard Android app architecture

## Key Features to Implement

1. **Main Page**: Two-column grid of picture cards with thumbnail images
2. **Image Detail Page**: Full-size image viewer with back navigation
3. **About Me Page**: Information about app creator/team
4. **Responsive Design**: Support for various screen sizes

## Development Commands

- **Build the project**: `./gradlew build`
- **Run on device/emulator**: `./gradlew installDebug`
- **Clean build**: `./gradlew clean`

## Development Status

Android project structure is now implemented with Jetpack Compose. The app includes all core features from the requirements:

- Two-column grid layout with picture cards
- Image detail view with full-screen display
- About page with app information
- Navigation between screens
- Sample images from Unsplash

## Project Structure

- `app/src/main/java/com/example/picturecardgallery/`
  - `MainActivity.kt` - Main activity with Compose setup
  - `PictureCardGalleryApp.kt` - Navigation host and routing
  - `data/PictureData.kt` - Sample image data
  - `ui/screens/` - Screen composables (MainPage, ImageDetailPage, AboutMePage)
  - `ui/components/` - Reusable UI components
  - `ui/theme/` - App theming and colors

## Future Enhancements

- Image upload functionality
- Favorites/bookmarks system
- Settings page