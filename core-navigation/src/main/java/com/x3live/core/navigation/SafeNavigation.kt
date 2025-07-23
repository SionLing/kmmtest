package com.x3live.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

/**
 * Simple extension functions for NavController that include built-in throttling
 * to prevent rapid navigation calls.
 */

private object NavigationThrottle {
    private const val THROTTLE_INTERVAL = 100L
    private var lastNavigationTime = 0L
    
    fun shouldAllowNavigation(): Boolean {
        val currentTime = System.currentTimeMillis()
        val canNavigate = currentTime - lastNavigationTime >= THROTTLE_INTERVAL
        
        if (canNavigate) {
            lastNavigationTime = currentTime
        }
        
        return canNavigate
    }
}

/**
 * Safe navigation that prevents rapid navigation calls
 * Usage: navController.safeNavigate("route")
 */
fun NavController.safeNavigate(
    route: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    if (!NavigationThrottle.shouldAllowNavigation()) return
    
    try {
        navigate(route) {
            launchSingleTop = true
            restoreState = true
            builder()
        }
    } catch (e: Exception) {
        // Silently handle navigation errors
    }
}

/**
 * Safe back navigation that prevents rapid back button presses
 * Usage: navController.safePopBackStack()
 */
fun NavController.safePopBackStack(): Boolean {
    if (!NavigationThrottle.shouldAllowNavigation()) return false
    
    // Check if we can go back (not at root)
    if (previousBackStackEntry == null) return false
    
    return try {
        popBackStack()
    } catch (e: Exception) {
        false
    }
}