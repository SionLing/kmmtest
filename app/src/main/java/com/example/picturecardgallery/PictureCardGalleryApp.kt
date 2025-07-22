package com.example.picturecardgallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.picturecardgallery.navigation.initializeRoutes
import com.x3live.core.navigation.rememberAppNavigator

@Composable
fun PictureCardGalleryApp() {
    val navController = rememberNavController()
    val navigator = rememberAppNavigator(navController)

    // Initialize routes only once, not on every recomposition
    LaunchedEffect(Unit) {
        initializeRoutes()
    }
    
    navigator.SetupNavigation()
}

@Preview(showBackground = true)
@Composable
fun PictureCardGalleryAppPreview() {
    PictureCardGalleryApp()
}
