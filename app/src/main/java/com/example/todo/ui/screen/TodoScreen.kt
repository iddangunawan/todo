package com.example.todo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.data.database.entity.TodoEntity
import com.example.todo.ui.viewmodel.TodoViewModel

@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel = hiltViewModel(),
) {
    val todoList: List<TodoEntity> by todoViewModel.getTodoList()
        .collectAsStateWithLifecycle(emptyList())

    if (todoList.isEmpty()) {
        println("todoList empty")
    } else {
        println("todoList $todoList")
    }
}