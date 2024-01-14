package com.example.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.database.dao.TodoDao
import com.example.todo.data.database.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}