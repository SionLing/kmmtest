package com.example.picturecardgallery.features.imagedetail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.x3live.core.navigation.safeNavigate
import com.example.picturecardgallery.features.imagedetail.ui.ImageDetailPage

object ImageDetailRoute : AppRoute("image_detail/{id}") {
    
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        val imageId = entry.arguments?.getString("id")?.toIntOrNull() ?: 0
        ImageDetailPage(
            imageId = imageId,
            onBackClick = navigationActions::navigateBack,
            navigationActions = navigationActions
        )
    }

    override fun go(navController: NavController, vararg params: Any) {
        val imageId = params.firstOrNull() as? Int ?: 0
        val destination = "image_detail/$imageId"
        navController.safeNavigate(destination)
    }
}