package com.example.picturecardgallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.picturecardgallery.ui.screens.AboutMePage
import com.example.picturecardgallery.ui.screens.ImageDetailPage
import com.example.picturecardgallery.ui.screens.MainPage

sealed class AppRoute(val route: String) {
    
    abstract fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit

    abstract fun go(navController: NavController, vararg params: Any)

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

    object ImageDetailRoute : AppRoute("image_detail/{id}") {
        override fun content(
            entry: NavBackStackEntry,
            navigationActions: NavigationActions
        ): @Composable () -> Unit = {
            val imageId = entry.arguments?.getString("id")?.toIntOrNull() ?: 0
            ImageDetailPage(
                imageId = imageId,
                onBackClick = navigationActions::navigateBack
            )
        }

        override fun go(navController: NavController, vararg params: Any) {
            val imageId = params.firstOrNull() as? Int ?: 0
            navController.navigate("image_detail/$imageId")
        }
    }

    companion object {
        val allRoutes = listOf(MainRoute, AboutRoute, ImageDetailRoute)
    }
}