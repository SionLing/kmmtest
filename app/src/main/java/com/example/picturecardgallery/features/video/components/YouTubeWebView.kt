package com.example.picturecardgallery.features.video.components

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun YouTubeWebView(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                // Enable hardware acceleration and WebGL
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = false
                settings.allowContentAccess = false
                settings.mediaPlaybackRequiresUserGesture = false
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.setSupportMultipleWindows(true)
                
                // Enable hardware acceleration for video rendering
                setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null)
                
                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        // Force a repaint after page load to prevent white screen
                        view?.invalidate()
                    }
                }
                
                // Convert YouTube URL to embeddable format if needed
                val embedUrl = convertToEmbedUrl(videoUrl)
                loadUrl(embedUrl)
            }
        },
        update = { webView ->
            val embedUrl = convertToEmbedUrl(videoUrl)
            if (webView.url != embedUrl) {
                webView.loadUrl(embedUrl)
            }
        }
    )
}

/**
 * Converts YouTube URLs to embeddable format
 * Supports:
 * - https://www.youtube.com/watch?v=VIDEO_ID
 * - https://youtu.be/VIDEO_ID
 * - https://www.youtube.com/shorts/VIDEO_ID
 * - Already embedded URLs
 */
private fun convertToEmbedUrl(url: String): String {
    return when {
        url.contains("youtube.com/watch?v=") -> {
            val videoId = url.substringAfter("watch?v=").substringBefore("&")
            "https://www.youtube.com/embed/$videoId?autoplay=1&controls=1&rel=0&modestbranding=1"
        }
        url.contains("youtu.be/") -> {
            val videoId = url.substringAfterLast("/").substringBefore("?")
            "https://www.youtube.com/embed/$videoId?autoplay=1&controls=1&rel=0&modestbranding=1"
        }
        url.contains("youtube.com/shorts/") -> {
            val videoId = url.substringAfter("shorts/").substringBefore("?")
            "https://www.youtube.com/embed/$videoId?autoplay=1&controls=1&rel=0&modestbranding=1"
        }
        url.contains("youtube.com/embed/") -> {
            // Already in embed format, just ensure proper parameters
            val baseUrl = url.substringBefore("?")
            "$baseUrl?autoplay=1&controls=1&rel=0&modestbranding=1"
        }
        else -> url // Return as-is if not a recognized YouTube URL
    }
}