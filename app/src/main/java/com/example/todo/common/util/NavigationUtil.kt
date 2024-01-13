package com.example.todo.common.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object NavigationUtil {
    fun navigateSingleTopTo(
        route: String,
        navController: NavHostController
    ) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }
}