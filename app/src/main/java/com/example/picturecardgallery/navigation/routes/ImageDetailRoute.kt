package com.example.picturecardgallery.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.picturecardgallery.navigation.AppRoute
import com.example.picturecardgallery.navigation.NavigationActions
import com.example.picturecardgallery.ui.screens.ImageDetailPage

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