# Navigation System

This directory contains the navigation system for the Picture Card Gallery app. The system is designed to be clean, maintainable, and avoid merge conflicts when multiple developers work on different routes.

## Architecture Overview

```
navigation/
├── AppRoute.kt                    # Base class for all routes
├── AppNavigator.kt               # Navigation controller setup
├── NavigationActions.kt          # Navigation interface
├── RouteLoader.kt               # Manual route loader
└── routes/
    ├── MainRoute.kt             # Home screen route
    ├── AboutRoute.kt            # About page route
    ├── ImageDetailRoute.kt      # Image detail route
    └── ProfileRoute.kt          # User profile route
```

## Core Components

### AppRoute.kt
Abstract base class that all routes extend. Provides:
- Route string definition
- Auto-registration with the navigation system
- Common navigation interface

### AppNavigator.kt  
Navigation controller that:
- Sets up the NavHost with all registered routes
- Handles navigation actions
- Uses RouteLoader to initialize routes

### RouteLoader.kt
Manual route loader that:
- Imports all route classes
- Forces route object initialization
- Must be updated when adding new routes

### NavigationActions.kt
Interface for navigation actions like `navigateBack()`

## How It Works

1. **Route Registration**: Each route object automatically registers itself when initialized
2. **Route Loading**: `RouteLoader.initialize()` forces all route objects to load
3. **Navigation Setup**: `AppNavigator` creates NavHost with all registered routes
4. **Route Resolution**: Navigation uses the registered routes for screen composition

## Adding a New Route

### Step 1: Create Route File
Create a new file in the `routes/` directory:

```kotlin
// routes/SettingsRoute.kt
package com.example.picturecardgallery.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.example.picturecardgallery.ui.screens.SettingsPage

object SettingsRoute : AppRoute("settings") {
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        SettingsPage(onBackClick = navigationActions::navigateBack)
    }

    override fun go(navController: NavController, vararg params: Any) {
        navController.navigate(route)
    }
}
```

### Step 2: Update RouteLoader
Add the import and reference to `RouteLoader.kt`:

```kotlin
import com.example.picturecardgallery.navigation.routes.SettingsRoute

object RouteLoader {
    fun initialize() {
        MainRoute
        AboutRoute
        ImageDetailRoute
        ProfileRoute
        SettingsRoute  // Add new route here
    }
}
```

That's it! The route is now available for navigation.

## Route Types

### Simple Route
```kotlin
object AboutRoute : AppRoute("about") {
    // Implementation...
}
```

### Parameterized Route
```kotlin
object ProfileRoute : AppRoute("profile/{userId}") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
        val userId = entry.arguments?.getString("userId") ?: "unknown"
        ProfileScreen(userId = userId)
    }
    
    override fun go(navController: NavController, vararg params: Any) {
        val userId = params.firstOrNull() as? String ?: "default"
        navController.navigate("profile/$userId")
    }
}
```

## Navigation Usage

### From Composable
```kotlin
// Get navigator from parent
@Composable
fun MyScreen(navigationActions: NavigationActions) {
    Button(onClick = { 
        ProfileRoute.go(navigationActions.navController, "user123")
    }) {
        Text("Go to Profile")
    }
}
```

### From Route
```kotlin
// In route content
MainPage(
    onImageClick = { imageId -> 
        ImageDetailRoute.go(navigationActions.navController, imageId) 
    },
    onAboutClick = { 
        AboutRoute.go(navigationActions.navController) 
    }
)
```

## Benefits

- **Separation of Concerns**: Each route is self-contained
- **Type Safety**: Compile-time route validation
- **No Merge Conflicts**: Routes in separate files
- **Auto-Registration**: Routes register themselves when loaded
- **Simple**: Easy to understand and maintain

## Common Patterns

### Back Navigation
```kotlin
override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
    MyScreen(onBackClick = navigationActions::navigateBack)
}
```

### Parameter Handling
```kotlin
override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
    val param = entry.arguments?.getString("paramName") ?: "default"
    MyScreen(parameter = param)
}
```

### Navigation with Parameters
```kotlin
override fun go(navController: NavController, vararg params: Any) {
    val id = params.firstOrNull() as? Int ?: 0
    navController.navigate("myroute/$id")
}
```

## Integration Notes

The system integrates with the main app through:
- `PictureCardGalleryApp.kt` calls `RouteLoader.initialize()`
- Routes extend from `com.x3live.core.navigation.AppRoute`
- Navigation actions use `com.x3live.core.navigation.NavigationActions`

This suggests integration with a shared navigation library (`x3live.core.navigation`).