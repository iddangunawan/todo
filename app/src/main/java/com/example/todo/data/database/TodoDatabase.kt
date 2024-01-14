package com.example.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.database.entity.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

}