package com.example.todo.data.database

import com.example.todo.data.database.entity.Todo
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getTodoList(): Flow<List<Todo>>
    suspend fun todoSave(todo: Todo)
    suspend fun todoDelete(todo: Todo)
}