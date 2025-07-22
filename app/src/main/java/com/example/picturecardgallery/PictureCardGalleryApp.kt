package com.example.picturecardgallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.picturecardgallery.navigation.rememberAppNavigator

@Composable
fun PictureCardGalleryApp() {
    val navController = rememberNavController()
    val navigator = rememberAppNavigator(navController)
    
    navigator.SetupNavigation()
}

@Preview(showBackground = true)
@Composable
fun PictureCardGalleryAppPreview() {
    PictureCardGalleryApp()
}
