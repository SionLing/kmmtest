package com.example.picturecardgallery.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.picturecardgallery.navigation.AppRoute
import com.example.picturecardgallery.navigation.NavigationActions
import com.example.picturecardgallery.ui.screens.MainPage

object MainRoute : AppRoute("main") {
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        MainPage(
            onImageClick = { imageId -> ImageDetailRoute.go(navigationActions.navController, imageId) },
            onAboutClick = { AboutRoute.go(navigationActions.navController) }
        )
    }

    override fun go(navController: NavController, vararg params: Any) {
        navController.navigate(route)
    }
}