package com.example.picturecardgallery.features.about.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.x3live.core.navigation.safeNavigate
import com.example.picturecardgallery.features.about.ui.AboutMePage

object AboutRoute : AppRoute("about") {
    
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        AboutMePage(onBackClick = navigationActions::navigateBack)
    }

    override fun go(navController: NavController, vararg params: Any) {
        navController.safeNavigate(route)
    }
}