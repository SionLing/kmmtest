package com.x3live.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class AppNavigator(override val navController: NavController) : NavigationActions {

    override fun navigateBack() {
        navController.popBackStack()
    }

    @Composable
    fun SetupNavigation() {
        NavHost(
            navController = navController as NavHostController,
            startDestination = "main"
        ) {
            AppRoute.allRoutes.forEach { route ->
                composable(route = route.route) { entry ->
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
