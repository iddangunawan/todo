package com.example.todo.ui.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val currentScreen = bottomBarScreens.find { it.route == currentDestination?.route } ?: Route.Todo

    val showDialogAddTodo = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (navController.currentBackStackEntry?.destination?.route == Route.Basic.route) {
                TodoTopBarWithBack(
                    onBackClicked = {
                        navController.popBackStack()
                    },
                )
            } else {
                TodoTopBar()
            }
        },
        bottomBar = {
            TodoBottomNavigation(
                currentScreen = currentScreen
            ) {
                navigateSingleTopTo(it.route, navController)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialogAddTodo.value = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(R.string.add_task),
                )
            }
        }
    ) {
        TodoNavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            showDialogAddTodo = showDialogAddTodo,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(id = R.string.app_name))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopBarWithBack(onBackClicked: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource(id = R.string.basic_test))
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
private fun TodoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    showDialogAddTodo: MutableState<Boolean>,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Todo.route,
        modifier = modifier
    ) {
        composable(route = Route.Todo.route) {
            TodoScreen(
                showDialogAddTodo = showDialogAddTodo,
            )
        }
        composable(route = Route.Basic.route) {
//            BasicTestScreen()
        }
    }
}

@Composable
fun TodoBottomNavigation(
    currentScreen: Route,
    onIconSelected: (Route) -> Unit,
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