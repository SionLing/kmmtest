package com.example.picturecardgallery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.activity.compose.BackHandler
import com.x3live.core.ui.throttledClick
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.picturecardgallery.data.PictureData
import com.example.picturecardgallery.video.VideoPlayerActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailPage(
    imageId: Int,
    onBackClick: () -> Unit
) {
    val picture = PictureData.sampleImages.find { it.id == imageId }
    val throttledBackClick = throttledClick(onBackClick)
    val context = LocalContext.current
    
    BackHandler {
        throttledBackClick()
    }
    
    if (picture == null) {
        throttledBackClick()
        return
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(picture.title)
                },
                navigationIcon = {
                    IconButton(onClick = throttledBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Show video button only if video URL is available
                    if (picture.videoUrl.isNotEmpty()) {
                        IconButton(
                            onClick = throttledClick {
                                val intent = VideoPlayerActivity.createIntent(
                                    context = context,
                                    videoUrl = picture.videoUrl,
                                    videoTitle = picture.title
                                )
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play Video"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = picture.imageUrl,
                contentDescription = picture.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = picture.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                
                if (picture.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = picture.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}