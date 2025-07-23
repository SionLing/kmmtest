package com.example.picturecardgallery.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.x3live.core.navigation.safeNavigate
import com.example.picturecardgallery.ui.screens.MainPage

object MainRoute : AppRoute("main") {
    
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        MainPage(
            onImageClick = { imageId -> 
                ImageDetailRoute.go(navigationActions.navController, imageId)
            },
            onAboutClick = { 
                AboutRoute.go(navigationActions.navController)
            }
        )
    }

    override fun go(navController: NavController, vararg params: Any) {
        navController.safeNavigate(route)
    }
}