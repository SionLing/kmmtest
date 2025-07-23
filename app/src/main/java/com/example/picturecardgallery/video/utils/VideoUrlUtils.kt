package com.example.picturecardgallery.video.utils

/**
 * Utility functions for handling video URLs
 */
object VideoUrlUtils {
    
    /**
     * Checks if the given URL is a valid YouTube URL
     */
    fun isYouTubeUrl(url: String): Boolean {
        return url.contains("youtube.com") || 
               url.contains("youtu.be") || 
               url.contains("youtube.com/shorts")
    }
    
    /**
     * Extracts video ID from various YouTube URL formats
     */
    fun extractVideoId(url: String): String? {
        return when {
            url.contains("youtube.com/watch?v=") -> {
                url.substringAfter("watch?v=").substringBefore("&")
            }
            url.contains("youtu.be/") -> {
                url.substringAfterLast("/").substringBefore("?")
            }
            url.contains("youtube.com/shorts/") -> {
                url.substringAfter("shorts/").substringBefore("?")
            }
            url.contains("youtube.com/embed/") -> {
                url.substringAfter("embed/").substringBefore("?")
            }
            else -> null
        }
    }
    
    /**
     * Creates a YouTube thumbnail URL from video ID
     */
    fun getThumbnailUrl(videoId: String, quality: ThumbnailQuality = ThumbnailQuality.MEDIUM): String {
        return "https://img.youtube.com/vi/$videoId/${quality.fileName}"
    }
    
    /**
     * Creates a direct YouTube watch URL from video ID
     */
    fun getWatchUrl(videoId: String): String {
        return "https://www.youtube.com/watch?v=$videoId"
    }
}

enum class ThumbnailQuality(val fileName: String) {
    DEFAULT("default.jpg"),           // 120x90
    MEDIUM("mqdefault.jpg"),          // 320x180
    HIGH("hqdefault.jpg"),            // 480x360
    STANDARD("sddefault.jpg"),        // 640x480
    MAXRES("maxresdefault.jpg")       // 1280x720
}