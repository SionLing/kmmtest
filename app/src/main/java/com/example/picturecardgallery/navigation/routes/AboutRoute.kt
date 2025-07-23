package com.example.picturecardgallery.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.x3live.core.navigation.NavigationThrottle
import com.example.picturecardgallery.ui.screens.AboutMePage

object AboutRoute : AppRoute("about") {
    
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        AboutMePage(onBackClick = navigationActions::navigateBack)
    }

    override fun go(navController: NavController, vararg params: Any) {
        if (!NavigationThrottle.canNavigate(route)) return
        
        NavigationThrottle.recordNavigation(route)
        navController.navigate(route) {
            launchSingleTop = true
        }
    }
}