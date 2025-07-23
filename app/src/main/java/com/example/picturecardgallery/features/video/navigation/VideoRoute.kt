package com.example.picturecardgallery.features.video.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import com.x3live.core.navigation.AppRoute
import com.x3live.core.navigation.NavigationActions
import com.x3live.core.navigation.safeNavigate
import com.example.picturecardgallery.features.video.ui.VideoPlayerScreen

object VideoRoute : AppRoute("video/{videoUrl}/{videoTitle}") {
    
    override fun content(
        entry: NavBackStackEntry,
        navigationActions: NavigationActions
    ): @Composable () -> Unit = {
        val videoUrl = entry.arguments?.getString("videoUrl")?.let { 
            java.net.URLDecoder.decode(it, "UTF-8") 
        } ?: ""
        val videoTitle = entry.arguments?.getString("videoTitle")?.let { 
            java.net.URLDecoder.decode(it, "UTF-8") 
        } ?: "Video"
        
        VideoPlayerScreen(
            videoUrl = videoUrl,
            videoTitle = videoTitle,
            onBackClick = navigationActions::navigateBack
        )
    }

    override fun go(navController: NavController, vararg params: Any) {
        val videoUrl = params.getOrNull(0) as? String ?: ""
        val videoTitle = params.getOrNull(1) as? String ?: "Video"
        
        val encodedUrl = java.net.URLEncoder.encode(videoUrl, "UTF-8")
        val encodedTitle = java.net.URLEncoder.encode(videoTitle, "UTF-8")
        
        navController.safeNavigate("video/$encodedUrl/$encodedTitle")
    }
    
    override fun deepLinks(): List<String> {
        return generateDeepLinks("video/{videoUrl}/{videoTitle}")
    }
}