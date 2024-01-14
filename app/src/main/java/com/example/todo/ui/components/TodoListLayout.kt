package com.example.todo.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import com.example.todo.data.database.entity.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoLayout(
    todoList: List<TodoEntity>,
    todoClicked: (TodoEntity) -> Unit,
    todoClickedDelete: (TodoEntity) -> Unit,
    deleteTodo: (TodoEntity) -> Unit,
) {
    LazyColumn {
        items(todoList, key = { todo -> todo.task!! }) { todo ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(direction = DismissDirection.EndToStart)
                || dismissState.isDismissed(direction = DismissDirection.StartToEnd)
            ) {
                deleteTodo(todo)
            }
            SwipeToDismiss(
                state = dismissState,
                background = {},
                dismissContent = {
                    Todo(
                        todo = todo,
                        onItemClick = {
                            todoClicked(it)
                        },
                        onItemClickDelete = {
                            todoClickedDelete(it)
                        }
                    )
                },
            )
        }
    }
}