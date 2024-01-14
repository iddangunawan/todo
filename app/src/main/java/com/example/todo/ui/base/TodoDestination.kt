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
    data object Todo : Route("todo", R.string.app_name, R.drawable.ic_edit_note)
    data object Basic : Route("basic", R.string.basic_test, R.drawable.ic_notes)
}

val bottomBarScreens = listOf(
    Route.Todo,
    Route.Basic,
)