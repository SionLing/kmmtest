package com.example.picturecardgallery.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.picturecardgallery.navigation.AppRoute
import com.example.picturecardgallery.navigation.NavigationActions
import com.example.picturecardgallery.ui.screens.AboutMePage

object AboutRoute : AppRoute("about") {
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        AboutMePage(onBackClick = navigationActions::navigateBack)
    }

    override fun go(navController: NavController, vararg params: Any) {
        navController.navigate(route)
    }
}