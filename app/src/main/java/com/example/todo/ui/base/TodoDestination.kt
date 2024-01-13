package com.example.todo.ui.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.todo.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int,
    val routeWithoutArgs: String = route
) {
    data object Home : Route("home", R.string.app_name, R.drawable.ic_home)
}

val bottomBarScreens = listOf(
    Route.Home,
)