package com.x3live.core.ui.listeners

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Simple global throttling to prevent rapid clicking across the entire app
 */
private object ClickThrottle {
    private var lastClickTime = 0L
    private const val THROTTLE_TIME_MS = 100L
    
    fun shouldAllowClick(): Boolean {
        val now = System.currentTimeMillis()
        return if (now - lastClickTime >= THROTTLE_TIME_MS) {
            lastClickTime = now
            true
        } else {
            false
        }
    }
}

/**
 * Simple wrapper for onClick functions that prevents rapid clicking
 * Usage: Button(onClick = throttledClick { doSomething() })
 */
@Composable
fun throttledClick(onClick: () -> Unit): () -> Unit {
    return remember(onClick) {
        {
            if (ClickThrottle.shouldAllowClick()) {
                onClick()
            }
        }
    }
}