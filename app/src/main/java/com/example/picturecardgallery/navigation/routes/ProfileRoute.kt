package com.example.picturecardgallery.navigation.routes

import android.util.Log
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
    private const val TAG = "ProfileRoute"
    
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        val userId = entry.arguments?.getString("userId") ?: "unknown"
        Log.d(TAG, "Loading profile page for userId: $userId")
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Profile for user: $userId\n\nThis route was auto-discovered!")
        }
    }

    override fun go(navController: NavController, vararg params: Any) {
        val userId = params.firstOrNull() as? String ?: "default"
        Log.d(TAG, "Navigating to profile route with userId: $userId")
        navController.navigate("profile/$userId")
    }
}