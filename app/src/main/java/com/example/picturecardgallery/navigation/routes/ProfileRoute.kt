package com.example.picturecardgallery.navigation.routes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions

object ProfileRoute : AppRoute("profile/{userId}") {
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        val userId = entry.arguments?.getString("userId") ?: "unknown"
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Profile for user: $userId\n\nThis route was auto-discovered!")
        }
    }

    override fun go(navController: NavController, vararg params: Any) {
        val userId = params.firstOrNull() as? String ?: "default"
        navController.navigate("profile/$userId")
    }
}