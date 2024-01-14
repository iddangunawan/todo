package com.example.todo.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var id by mutableIntStateOf(0)
        private set

    fun updateIdInput(input: Int) {
        id = input
    }

    var task by mutableStateOf("")
        private set

    fun updateTaskInput(input: String) {
        task = input
    }

    var notes by mutableStateOf("")
        private set

    fun updateNotesInput(input: String) {
        notes = input
    }

    fun resetForm(todo: TodoEntity?) {
        id = todo?.id ?: 0
        task = todo?.task ?: ""
        notes = todo?.notes ?: ""
    }

    fun getTodoList() = todoRepository.getTodoList()

    fun upsertTodo(todo: TodoEntity) = viewModelScope.launch(dispatcherProvider.io) {
        todoRepository.upsertTodo(todo)
    }

    fun deleteTodo(todo: TodoEntity) = viewModelScope.launch(dispatcherProvider.io) {
        todoRepository.deleteTodo(todo)
    }
}