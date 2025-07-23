package com.example.picturecardgallery.shared.navigation

import com.example.picturecardgallery.features.about.navigation.AboutRoute
import com.example.picturecardgallery.features.imagedetail.navigation.ImageDetailRoute
import com.example.picturecardgallery.features.gallery.navigation.MainRoute
import com.example.picturecardgallery.features.video.navigation.VideoRoute
import com.x3live.core.navigation.AppRoute

/**
 * Initialize routes and deeplink configuration.
 * Optimized to run only once and avoid repeated configuration.
 */
private var isInitialized = false

fun initializeRoutes() {
    if (isInitialized) return // Prevent multiple initialization

    // Configure deeplink scheme-host pairs based on AndroidManifest.xml
    AppRoute.configureDeeplinks(
        listOf(
            "https" to "gallery.app",
            "picturegallery" to "gallery.app"
        ))

    // Load route objects
    MainRoute
    AboutRoute
    ImageDetailRoute
    VideoRoute

    isInitialized = true
}
