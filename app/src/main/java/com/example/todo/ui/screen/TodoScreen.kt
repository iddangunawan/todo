package com.example.todo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.R
import com.example.todo.data.database.entity.TodoEntity
import com.example.todo.ui.base.ShowWarning
import com.example.todo.ui.viewmodel.TodoViewModel

@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel = hiltViewModel(),
) {
    val todoList: List<TodoEntity> by todoViewModel.getTodoList()
        .collectAsStateWithLifecycle(emptyList())

    if (todoList.isEmpty()) {
        ShowWarning(text = stringResource(R.string.empty_todo))
    } else {
        println("todoList $todoList")
    }
}