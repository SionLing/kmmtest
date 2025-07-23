package com.x3live.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink

class AppNavigator(override val navController: NavController) : NavigationActions {
    
    override fun navigateBack() {
        navController.safePopBackStack()
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
                    route.content(entry, this@AppNavigator)()
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

