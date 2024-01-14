package com.example.todo.data.repository

import com.example.todo.data.database.DatabaseService
import com.example.todo.data.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val database: DatabaseService,
) {
    fun getTodoList(): Flow<List<TodoEntity>> = database.getTodoList()
    suspend fun upsertTodo(todo: TodoEntity) = database.upsertTodo(todo)
    suspend fun deleteTodo(todo: TodoEntity) = database.deleteTodo(todo)
}