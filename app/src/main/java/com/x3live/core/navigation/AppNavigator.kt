package com.x3live.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink

class AppNavigator(override val navController: NavController) : NavigationActions {
    
    private var lastBackNavigationTime = 0L
    private var isNavigatingBack = false

    override fun navigateBack() {
        val currentTime = System.currentTimeMillis()
        val timeSinceLastBack = currentTime - lastBackNavigationTime
        
        // Check if we can go back at all
        val canGoBack = navController.previousBackStackEntry != null
        if (!canGoBack) return
        
        // Prevent rapid back navigation calls
        if (isNavigatingBack || timeSinceLastBack < 300L) return
        
        isNavigatingBack = true
        lastBackNavigationTime = currentTime
        
        try {
            navController.popBackStack()
        } catch (e: Exception) {
            // Silently handle navigation errors
        } finally {
            // Clear the flag after navigation
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                isNavigatingBack = false
            }, 300L)
        }
    }

    @Composable
    fun SetupNavigation() {
        val routes = remember { AppRoute.allRoutes }
        
        NavHost(
            navController = navController as NavHostController,
            startDestination = "main"
        ) {
            routes.forEach { route ->
                composable(
                    route = route.route,
                    deepLinks = route.deepLinks().map { uri ->
                        navDeepLink { uriPattern = uri }
                    }
                ) { entry ->
                    route.content(
                        entry = entry,
                        navigationActions = this@AppNavigator
                    )()
                }
            }
        }
    }
}

@Composable
fun rememberAppNavigator(navController: NavHostController): AppNavigator {
    return remember(navController) {
        AppNavigator(navController)
    }
}
