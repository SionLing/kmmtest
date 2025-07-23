package com.example.picturecardgallery.video.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.picturecardgallery.video.utils.VideoUrlUtils
import com.example.picturecardgallery.video.utils.ThumbnailQuality

/**
 * A composable that displays a video thumbnail with a play button overlay
 */
@Composable
fun VideoThumbnail(
    videoUrl: String,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    thumbnailQuality: ThumbnailQuality = ThumbnailQuality.MEDIUM
) {
    val videoId = VideoUrlUtils.extractVideoId(videoUrl)
    val thumbnailUrl = videoId?.let { 
        VideoUrlUtils.getThumbnailUrl(it, thumbnailQuality) 
    }
    
    Card(
        modifier = modifier.clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Video thumbnail
            if (thumbnailUrl != null) {
                AsyncImage(
                    model = thumbnailUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Fallback for invalid video URLs
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Video",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            
            // Play button overlay
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp),
                shape = RoundedCornerShape(28.dp),
                color = Color.Black.copy(alpha = 0.7f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Video",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            
            // Video duration badge (placeholder - would need video metadata to implement)
            // You could add video duration here if you have that data
        }
    }
}