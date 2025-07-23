package com.example.picturecardgallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.picturecardgallery.navigation.initializeRoutes
import com.x3live.core.navigation.rememberAppNavigator

@Composable
fun PictureCardGalleryApp() {
    val navController = rememberNavController()
    val navigator = rememberAppNavigator(navController)
    var routesInitialized by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        initializeRoutes()
        routesInitialized = true
    }
    
    if (routesInitialized) {
        navigator.SetupNavigation()
    }
}

@Preview(showBackground = true)
@Composable
fun PictureCardGalleryAppPreview() {
    PictureCardGalleryApp()
}
