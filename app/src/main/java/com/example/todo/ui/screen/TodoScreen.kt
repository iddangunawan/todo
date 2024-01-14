package com.example.todo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.R
import com.example.todo.data.database.entity.TodoEntity
import com.example.todo.ui.base.ShowWarning
import com.example.todo.ui.components.DialogAddTodo
import com.example.todo.ui.components.TodoLayout
import com.example.todo.ui.viewmodel.TodoViewModel

@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel = hiltViewModel(),
    showDialogAddTodo: MutableState<Boolean>,
) {
    val todoList: List<TodoEntity> by todoViewModel.getTodoList()
        .collectAsStateWithLifecycle(emptyList())

    if (todoList.isEmpty()) {
        ShowWarning(text = stringResource(R.string.empty_todo))
    } else {
        TodoLayout(
            todoList = todoList,
            todoClicked = {
                showDialogAddTodo.value = true
                // For repopulated existing data to form
                todoViewModel.resetForm(it)
            },
            todoClickedDelete = {
                todoViewModel.deleteTodo(it)
            },
            deleteTodo = {
                todoViewModel.deleteTodo(it)
            },
        )
    }
    DialogAddTodo(
        todoViewModel = todoViewModel,
        openDialog = showDialogAddTodo.value,
        onDismissRequest = {
            showDialogAddTodo.value = false
            todoViewModel.resetForm(null)
        },
        onUpsert = {
            todoViewModel.upsertTodo(it)
            showDialogAddTodo.value = false
        },
    )
}