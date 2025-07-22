# X3Live Core Navigation Library

A lightweight, type-safe navigation library for Android Compose applications. This library provides a clean architecture for managing navigation with automatic route registration and minimal boilerplate.

## Overview

The X3Live Core Navigation library simplifies navigation by providing:
- **Type-safe routing** with compile-time validation
- **Automatic route registration** through object initialization
- **Clean separation of concerns** with individual route files
- **Minimal boilerplate** for adding new routes
- **Conflict-free development** with separate route files

## Architecture

```
com.x3live.core.navigation/
├── AppRoute.kt              # Abstract base class for all routes
├── NavigationActions.kt     # Navigation interface
└── AppNavigator.kt         # Navigation utilities
```

## Core Components

### AppRoute
Abstract base class that all routes must extend:

```kotlin
abstract class AppRoute(val route: String) {
    abstract fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit

    abstract fun go(navController: NavController, vararg params: Any)
    
    // Auto-registration happens in init block
    init { register(this) }
    
    companion object {
        val allRoutes: List<AppRoute> // All registered routes
    }
}
```

### NavigationActions
Interface for navigation operations:

```kotlin
interface NavigationActions {
    val navController: NavController
    fun navigateBack()
}
```

### AppNavigator
Utility functions for navigation setup:

```kotlin
@Composable
fun rememberAppNavigator(navController: NavHostController): AppNavigator
```

## Quick Start

### 1. Create a Route

```kotlin
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions

object HomeRoute : AppRoute("home") {
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        HomeScreen(
            onSettingsClick = { SettingsRoute.go(navigationActions.navController) }
        )
    }

    override fun go(navController: NavController, vararg params: Any) {
        navController.navigate(route)
    }
}
```

### 2. Setup Navigation

```kotlin
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val navigator = rememberAppNavigator(navController)
    
    // Initialize routes (force object loading)
    RouteLoader.initialize()
    
    navigator.SetupNavigation()
}
```

### 3. Create Route Loader

```kotlin
object RouteLoader {
    fun initialize() {
        // Reference route objects to trigger initialization
        HomeRoute
        SettingsRoute
        ProfileRoute
    }
}
```

## Route Types

### Simple Route
Routes without parameters:

```kotlin
object AboutRoute : AppRoute("about") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
        AboutScreen(onBackClick = navigationActions::navigateBack)
    }
    
    override fun go(navController: NavController, vararg params: Any) {
        navController.navigate("about")
    }
}
```

### Parameterized Route
Routes with path parameters:

```kotlin
object UserRoute : AppRoute("user/{userId}") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
        val userId = entry.arguments?.getString("userId") ?: ""
        UserScreen(
            userId = userId,
            onBackClick = navigationActions::navigateBack
        )
    }
    
    override fun go(navController: NavController, vararg params: Any) {
        val userId = params.firstOrNull() as? String ?: ""
        navController.navigate("user/$userId")
    }
}
```

### Query Parameters
Routes with optional query parameters:

```kotlin
object SearchRoute : AppRoute("search?query={query}") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
        val query = entry.arguments?.getString("query") ?: ""
        SearchScreen(
            initialQuery = query,
            onBackClick = navigationActions::navigateBack
        )
    }
    
    override fun go(navController: NavController, vararg params: Any) {
        val query = params.firstOrNull() as? String ?: ""
        navController.navigate("search?query=$query")
    }
}
```

## Navigation Patterns

### Basic Navigation
```kotlin
// In a Composable
Button(onClick = { 
    HomeRoute.go(navigationActions.navController) 
}) {
    Text("Go Home")
}
```

### Navigation with Parameters
```kotlin
// Navigate with user ID
UserRoute.go(navigationActions.navController, "user123")

// Navigate with search query
SearchRoute.go(navigationActions.navController, "android development")
```

### Back Navigation
```kotlin
// Standard back navigation
navigationActions.navigateBack()

// Or in route content
MyScreen(onBackClick = navigationActions::navigateBack)
```

### Conditional Navigation
```kotlin
// Navigate based on condition
if (user.isLoggedIn) {
    DashboardRoute.go(navController)
} else {
    LoginRoute.go(navController)
}
```

## Advanced Features

### Route Registration
Routes are automatically registered when their object is first accessed:

```kotlin
// This triggers registration
val routes = AppRoute.allRoutes // Contains all initialized routes
```

### Custom Navigation Options
```kotlin
override fun go(navController: NavController, vararg params: Any) {
    navController.navigate(route) {
        // Custom navigation options
        popUpTo("main") { inclusive = false }
        launchSingleTop = true
        restoreState = true
    }
}
```

### Nested Navigation
```kotlin
object NestedRoute : AppRoute("nested/{section}") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions) = {
        val section = entry.arguments?.getString("section") ?: "default"
        
        // Nested navigation setup
        NestedNavHost(
            startDestination = section,
            navigationActions = navigationActions
        )
    }
}
```

## Best Practices

### 1. Route Organization
- Keep routes in separate files
- Use descriptive route names
- Group related routes in packages

### 2. Parameter Handling
```kotlin
// Always provide defaults
val userId = entry.arguments?.getString("userId") ?: "unknown"

// Validate parameters
val itemId = entry.arguments?.getString("itemId")?.toIntOrNull() ?: 0
```

### 3. Type Safety
```kotlin
// Use sealed classes for route parameters
sealed class RouteParams {
    data class User(val id: String) : RouteParams()
    data class Item(val id: Int) : RouteParams()
}
```

### 4. Error Handling
```kotlin
override fun go(navController: NavController, vararg params: Any) {
    try {
        val param = params.firstOrNull() as? String 
            ?: throw IllegalArgumentException("Missing required parameter")
        navController.navigate("route/$param")
    } catch (e: Exception) {
        // Handle navigation errors
        Log.e("Navigation", "Failed to navigate", e)
    }
}
```

## Integration

### Dependency Setup
Add the core navigation to your module:

```kotlin
// In your app's navigation package
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.x3live.core.navigation.rememberAppNavigator
```

### Multi-Module Projects
Routes can be distributed across modules:

```kotlin
// feature-auth module
object LoginRoute : AppRoute("login") { ... }

// feature-profile module  
object ProfileRoute : AppRoute("profile") { ... }

// app module - RouteLoader
object RouteLoader {
    fun initialize() {
        LoginRoute      // from feature-auth
        ProfileRoute    // from feature-profile
        HomeRoute       // from app module
    }
}
```

## Migration Guide

### From Manual Navigation
1. Extend `AppRoute` instead of manual route definitions
2. Replace string-based navigation with `route.go()`
3. Use `NavigationActions` instead of direct NavController access

### From Other Libraries
1. Convert route definitions to `AppRoute` objects
2. Update navigation calls to use the `go()` method
3. Implement `RouteLoader` for route initialization

## Performance Notes

- Route objects are singletons - created once and reused
- Route registration happens at object initialization (lazy)
- Navigation composition is cached by Compose Navigation
- No runtime reflection or dynamic route discovery

## Troubleshooting

### Routes Not Found
Ensure routes are initialized in `RouteLoader`:
```kotlin
object RouteLoader {
    fun initialize() {
        MyRoute // Must reference the object
    }
}
```

### Parameter Issues
Check parameter names match route definition:
```kotlin
// Route definition
object MyRoute : AppRoute("item/{itemId}")

// Parameter access
val itemId = entry.arguments?.getString("itemId") // Match exactly
```

### Navigation Loops
Avoid circular navigation patterns:
```kotlin
// Bad: A -> B -> A creates a loop
// Good: A -> B, with proper back handling
```

## License

This library is part of the X3Live Core framework.