package com.example.picturecardgallery.navigation

import com.example.picturecardgallery.navigation.routes.AboutRoute
import com.example.picturecardgallery.navigation.routes.ImageDetailRoute
import com.example.picturecardgallery.navigation.routes.MainRoute
import com.example.picturecardgallery.navigation.routes.ProfileRoute
import com.x3live.core.navigation.AppRoute

/**
 * Route loader - manually maintained.
 * Add new route imports and references here when creating new routes.
 */
object RouteLoader {
    fun initialize() {
        // Configure deeplink scheme-host pairs based on AndroidManifest.xml
        AppRoute.configureDeeplinks(listOf(
            "https" to "gallery.app",
            "picturegallery" to "gallery.app"
        ))
        
        // Force route object initialization by referencing them
        MainRoute
        AboutRoute
        ImageDetailRoute
        ProfileRoute
    }
}
