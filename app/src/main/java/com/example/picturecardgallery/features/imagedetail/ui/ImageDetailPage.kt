package com.example.picturecardgallery.features.imagedetail.ui

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
import com.x3live.core.ui.listeners.throttledClick
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.picturecardgallery.features.gallery.data.PictureData
import com.example.picturecardgallery.features.video.navigation.VideoRoute
import com.x3live.core.navigation.NavigationActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailPage(
    imageId: Int,
    onBackClick: () -> Unit,
    navigationActions: NavigationActions
) {
    val picture = PictureData.sampleImages.find { it.id == imageId }
    val throttledBackClick = throttledClick(onBackClick)
    
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
                                VideoRoute.go(
                                    navController = navigationActions.navController,
                                    picture.videoUrl,
                                    picture.title
                                )
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