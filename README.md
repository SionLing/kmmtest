# X3Live Framework

A reusable Android Compose navigation framework that provides:

- Type-safe navigation with route abstraction
- Built-in click and navigation throttling
- Deep link support
- Centralized route management

## Features

### 1. AppRoute System
Base class for defining navigation routes with built-in deep link support.

```kotlin
object MyRoute : AppRoute("my_route/{param}") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions): @Composable () -> Unit = {
        MyScreen(onBackClick = navigationActions::navigateBack)
    }
    
    override fun go(navController: NavController, vararg params: Any) {
        navController.safeNavigate("my_route/${params[0]}")
    }
}
```

### 2. Safe Navigation
Prevents navigation spam and handles errors gracefully.

```kotlin
navController.safeNavigate("destination") {
    launchSingleTop = true
    restoreState = true
}
```

### 3. Click Throttling
Prevents rapid button clicks that could cause navigation issues.

```kotlin
@Composable
fun MyButton() {
    Button(onClick = throttledClick { 
        // Your click action here
    }) {
        Text("Click me")
    }
}
```

### 4. Navigation Actions
Clean interface for navigation operations.

```kotlin
class AppNavigator(override val navController: NavController) : NavigationActions {
    override fun navigateBack(): Boolean = navController.safePopBackStack()
}
```

## Usage

### 1. Add to your project

In your app's `build.gradle`:
```gradle
dependencies {
    implementation project(':x3live-framework')
}
```

### 2. Define Routes

```kotlin
object HomeRoute : AppRoute("home") {
    override fun content(entry: NavBackStackEntry, navigationActions: NavigationActions): @Composable () -> Unit = {
        HomeScreen(navigationActions = navigationActions)
    }
}
```

### 3. Set up Navigation

```kotlin
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val navigator = remember { AppNavigator(navController) }
    
    NavHost(navController = navController, startDestination = HomeRoute.route) {
        HomeRoute.addToNavHost(this, navigator)
        // Add other routes...
    }
}
```

### 4. Use Deep Links

Configure deep links in your route loader:
```kotlin
AppRoute.configureDeeplinks(
    listOf(
        "https" to "myapp.com",
        "myapp" to "myapp.com"
    )
)
```

## Architecture

- **AppRoute**: Abstract base for all navigation routes
- **NavigationActions**: Interface for navigation operations  
- **SafeNavigation**: Extensions for safe navigation with throttling
- **ClickThrottle**: Global click throttling system

## Dependencies

- androidx.navigation:navigation-compose
- androidx.compose.ui
- androidx.compose.material3

## License

This is the X3Live Framework - a reusable Android Compose navigation library.