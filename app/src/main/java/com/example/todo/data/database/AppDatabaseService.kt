package com.example.todo.data.database

import com.example.todo.data.database.entity.TodoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

class AppDatabaseService(
    private val todoDatabase: TodoDatabase,
) : DatabaseService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getTodoList(): Flow<List<TodoEntity>> {
        return todoDatabase.getTodoDao().getTodoList().flatMapConcat {
            flow {
                val list = mutableListOf<TodoEntity>()
                it.forEach { todoEntity ->
                    list.add(todoEntity)
                }
                emit(list)
            }
        }
    }

    override suspend fun upsertTodo(todo: TodoEntity) {
        todoDatabase.getTodoDao().upsertTodo(todo)
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        todoDatabase.getTodoDao().deleteTodo(todo)
    }
}