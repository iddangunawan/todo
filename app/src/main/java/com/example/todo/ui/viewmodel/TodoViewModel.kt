package com.example.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.common.dispatcher.DispatcherProvider
import com.example.todo.data.database.entity.TodoEntity
import com.example.todo.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    fun getTodoList() = todoRepository.getTodoList()

    fun upsertTodo(todo: TodoEntity) = viewModelScope.launch(dispatcherProvider.io) {
        todoRepository.upsertTodo(todo)
    }

    fun deleteTodo(todo: TodoEntity) = viewModelScope.launch(dispatcherProvider.io) {
        todoRepository.deleteTodo(todo)
    }
}