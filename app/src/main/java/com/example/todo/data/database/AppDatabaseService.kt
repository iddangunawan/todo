package com.example.todo.data.database

import com.example.todo.data.database.entity.Todo
import kotlinx.coroutines.flow.Flow

class AppDatabaseService(
    private val todoDatabase: TodoDatabase,
) : DatabaseService {
    override fun getTodoList(): Flow<List<Todo>> {
        TODO("Not yet implemented")
    }

    override suspend fun todoSave(todo: Todo) {
        TODO("Not yet implemented")
    }

    override suspend fun todoDelete(todo: Todo) {
        TODO("Not yet implemented")
    }
}