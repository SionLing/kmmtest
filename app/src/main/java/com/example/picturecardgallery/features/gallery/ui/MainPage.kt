package com.example.picturecardgallery.features.gallery.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.picturecardgallery.features.gallery.data.PictureData
import com.example.picturecardgallery.features.gallery.ui.PictureCard
import androidx.compose.ui.graphics.Color
import com.x3live.core.ui.throttledClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    onImageClick: (Int) -> Unit,
    onAboutClick: () -> Unit
) {
    val throttledAboutClick = throttledClick(onAboutClick)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Picture Gallery")
                },
                actions = {
                    IconButton(onClick = throttledAboutClick) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "About Me"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(PictureData.sampleImages) { picture ->
                PictureCard(
                    picture = picture,
                    onClick = throttledClick { onImageClick(picture.id) },
                    descriptionColor = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    MainPage(
        onImageClick = {},
        onAboutClick = {}
    )
}
