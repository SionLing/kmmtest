package com.x3live.core.navigation

import androidx.navigation.NavController

interface NavigationActions {
    val navController: NavController
    fun navigateBack()
}