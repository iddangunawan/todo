package com.example.todo.data.database

import com.example.todo.data.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getTodoList(): Flow<List<TodoEntity>>
    suspend fun upsertTodo(todo: TodoEntity)
    suspend fun deleteTodo(todo: TodoEntity)
}