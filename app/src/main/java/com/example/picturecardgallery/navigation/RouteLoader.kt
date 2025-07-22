package com.example.picturecardgallery.navigation

import com.example.picturecardgallery.navigation.routes.AboutRoute
import com.example.picturecardgallery.navigation.routes.ImageDetailRoute
import com.example.picturecardgallery.navigation.routes.MainRoute
import com.example.picturecardgallery.navigation.routes.ProfileRoute

/**
 * Route loader - manually maintained.
 * Add new route imports and references here when creating new routes.
 */
object RouteLoader {
    fun initialize() {
        // Force route object initialization by referencing them
        MainRoute
        AboutRoute
        ImageDetailRoute
        ProfileRoute
    }
}
