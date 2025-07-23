package com.example.picturecardgallery.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.picturecardgallery.video.ui.VideoPlayerScreen

class VideoPlayerActivity : ComponentActivity() {
    
    companion object {
        private const val EXTRA_VIDEO_URL = "video_url"
        private const val EXTRA_VIDEO_TITLE = "video_title"
        
        fun createIntent(context: Context, videoUrl: String, videoTitle: String): Intent {
            return Intent(context, VideoPlayerActivity::class.java).apply {
                putExtra(EXTRA_VIDEO_URL, videoUrl)
                putExtra(EXTRA_VIDEO_TITLE, videoTitle)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val videoUrl = intent.getStringExtra(EXTRA_VIDEO_URL) ?: ""
        val videoTitle = intent.getStringExtra(EXTRA_VIDEO_TITLE) ?: "Video"
        
        setContent {
            VideoPlayerScreen(
                videoUrl = videoUrl,
                videoTitle = videoTitle,
                onBackClick = { finish() }
            )
        }
    }
}