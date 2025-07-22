package com.x3live.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

abstract class AppRoute(val route: String) {
    
    abstract fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit

    abstract fun go(navController: NavController, vararg params: Any)
    
    /**
     * Override this method to provide custom deeplink URIs for this route.
     * Default implementation auto-generates deeplinks from route path.
     */
    open fun deepLinks(): List<String> = listOf(
        "https://picturegallery.app/$route",
        "picturegallery://$route"
    )

    init {
        register(this)
    }

    companion object {
        private val _allRoutes = mutableListOf<AppRoute>()
        val allRoutes: List<AppRoute> get() = _allRoutes.toList()

        private fun register(route: AppRoute) {
            if (_allRoutes.none { it.route == route.route }) {
                _allRoutes.add(route)
            }
        }
    }
}
