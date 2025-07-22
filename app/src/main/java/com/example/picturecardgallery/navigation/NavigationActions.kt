package com.example.picturecardgallery.navigation

import androidx.navigation.NavController

interface NavigationActions {
    val navController: NavController
    fun navigateBack()
}