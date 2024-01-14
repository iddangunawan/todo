package com.example.todo.ui.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todo.R
import com.example.todo.common.util.NavigationUtil.navigateSingleTopTo
import com.example.todo.ui.screen.TodoScreen

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentScreen =
        bottomBarScreens.find { it.route == currentDestination?.route } ?: Route.Todo

    Scaffold(
        topBar = {
            TodoTopBar {
                if (navController.currentBackStackEntry?.destination?.route == Route.Basic.route
                    || navController.currentBackStackEntry?.destination?.route == Route.Todo.route
                ) {
                    navController.popBackStack()
                } else {
                    navigateSingleTopTo(Route.Todo.route, navController)
                }
            }
        },
        bottomBar = {
            TodoBottomNavigation(
                currentScreen = currentScreen
            ) {
                navigateSingleTopTo(it.route, navController)
            }
        }
    ) {
        TodoNavHost(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopBar(onBackClicked: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun TodoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Todo.route,
        modifier = modifier
    ) {
        composable(route = Route.Todo.route) {
            TodoScreen()
        }
        composable(route = Route.Basic.route) {
//            BasicTestScreen()
        }
    }
}

@Composable
fun TodoBottomNavigation(
    currentScreen: Route,
    onIconSelected: (Route) -> Unit
) {
    NavigationBar {
        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                selected = screen == currentScreen,
                label = {
                    Text(text = stringResource(id = screen.resourceId))
                },
                icon = {
                    Icon(painter = painterResource(id = screen.icon), null)
                },
                onClick = {
                    onIconSelected.invoke(screen)
                }
            )
        }
    }
}