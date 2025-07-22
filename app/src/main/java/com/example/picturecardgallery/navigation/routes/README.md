# Routes Directory

This directory contains individual route objects that extend from `AppRoute`. Each route is automatically registered when first referenced.

## How it works

1. **Auto-registration**: When a route object is first referenced, it automatically registers itself with the base `AppRoute` class through the `init` block
2. **Singleton pattern**: Routes are defined as `object` declarations, ensuring single instances
3. **Separate files**: Each route lives in its own file to prevent merge conflicts when multiple developers work on different routes
4. **Type safety**: All routes follow the same interface defined in `AppRoute`

## Adding a new route

1. Create a new file in this directory (e.g., `ProfileRoute.kt`)
2. Define as an `object` extending `AppRoute` and implement the required methods
3. Add a reference to the route in `AppNavigator.kt` to ensure it gets loaded

Example:
```kotlin
object ProfileRoute : AppRoute("profile/{userId}") {
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        val userId = entry.arguments?.getString("userId") ?: ""
        ProfileScreen(userId = userId, onBackClick = navigationActions::navigateBack)
    }

    override fun go(navController: NavController, vararg params: Any) {
        val userId = params.firstOrNull() as? String ?: ""
        navController.navigate("profile/$userId")
    }
}
```

## Current Routes

- `MainRoute.kt` - Home screen with picture grid
- `AboutRoute.kt` - About page
- `ImageDetailRoute.kt` - Full-size image viewer