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
     * Default implementation auto-generates deeplinks using configured schemes and hosts.
     */
    open fun deepLinks(): List<String> = generateDeepLinks(route)

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
        
        // Deeplink configuration - scheme:host pairs (initialized as empty)
        private var _deeplinkPairs = emptyList<Pair<String, String>>()
        
        /**
         * Configure deeplink scheme-host pairs
         * @param pairs List of scheme to host pairs (e.g., "myapp" to "mycompany.com")
         */
        fun configureDeeplinks(pairs: List<Pair<String, String>>) {
            _deeplinkPairs = pairs
        }
        
        /**
         * Get configured deeplink pairs
         */
        val deeplinkPairs: List<Pair<String, String>> get() = _deeplinkPairs
        
        /**
         * Generate deeplink URIs for a given route path
         */
        fun generateDeepLinks(routePath: String): List<String> {
            return _deeplinkPairs.map { (scheme, host) ->
                "$scheme://$host/$routePath"
            }
        }
    }
}
