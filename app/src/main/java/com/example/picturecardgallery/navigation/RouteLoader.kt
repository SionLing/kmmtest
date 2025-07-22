package com.example.picturecardgallery.navigation

import com.example.picturecardgallery.navigation.routes.AboutRoute
import com.example.picturecardgallery.navigation.routes.ImageDetailRoute
import com.example.picturecardgallery.navigation.routes.MainRoute
import com.example.picturecardgallery.navigation.routes.ProfileRoute
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

    MainRoute
    AboutRoute
    ImageDetailRoute
    ProfileRoute

    isInitialized = true
}
