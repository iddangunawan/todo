package com.example.todo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo",
    indices = [
        Index(
            value = ["task", "notes", "status"],
            unique = true
        )
    ]
)

data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int = 0,

    @ColumnInfo("task")
    val task: String?,

    @ColumnInfo("notes")
    val notes: String?,

    @ColumnInfo("status")
    val status: Boolean?,
)