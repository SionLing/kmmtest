package com.example.picturecardgallery

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.picturecardgallery.ui.screens.AboutMePage
import com.example.picturecardgallery.ui.screens.ImageDetailPage
import com.example.picturecardgallery.ui.screens.MainPage

@Composable
fun PictureCardGalleryApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainPage(
                onImageClick = { imageId ->
                    navController.navigate("image_detail/$imageId")
                },
                onAboutClick = {
                    navController.navigate("about")
                }
            )
        }
        
        composable("image_detail/{imageId}") { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")?.toIntOrNull() ?: 0
            ImageDetailPage(
                imageId = imageId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("about") {
            AboutMePage(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PictureCardGalleryAppPreview() {
    PictureCardGalleryApp()
}
