package com.x3live.core.navigation

object NavigationThrottle {
    private const val MIN_NAVIGATION_INTERVAL = 300L // 300ms minimum between navigations
    
    private var lastNavigationTime = 0L
    private var lastDestination = ""
    private var isNavigating = false
    
    fun canNavigate(destination: String): Boolean {
        val currentTime = System.currentTimeMillis()
        val timeSinceLastNavigation = currentTime - lastNavigationTime
        
        // Block navigation if we're currently in the middle of a navigation
        if (isNavigating) return false
        
        // Block rapid navigation to the same destination
        if (destination == lastDestination && timeSinceLastNavigation < MIN_NAVIGATION_INTERVAL) {
            return false
        }
        
        // Block any navigation if too fast (regardless of destination)
        if (timeSinceLastNavigation < 100L) return false
        
        return true
    }
    
    fun recordNavigation(destination: String) {
        val currentTime = System.currentTimeMillis()
        lastNavigationTime = currentTime
        lastDestination = destination
        isNavigating = true
        
        // Clear the navigating flag after a short delay to allow navigation to complete
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            isNavigating = false
        }, 200L)
    }
    
    fun clearState() {
        lastNavigationTime = 0L
        lastDestination = ""
        isNavigating = false
    }
}