package com.example.todo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.todo.R
import com.example.todo.data.database.entity.TodoEntity
import com.example.todo.ui.viewmodel.TodoViewModel

@Composable
fun DialogAddTodo(
    todoViewModel: TodoViewModel,
    dialogProperties: DialogProperties = DialogProperties(),
    openDialog: Boolean = false,
    onDismissRequest: () -> Unit = {},
    onUpsert: (TodoEntity) -> Unit,
) {
    when {
        openDialog -> {
            var isTaskError by remember { mutableStateOf(false) }

            Dialog(
                onDismissRequest = onDismissRequest,
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false,
                    securePolicy = dialogProperties.securePolicy,
                    usePlatformDefaultWidth = false
                ),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(
                                if (todoViewModel.id != 0) {
                                    R.string.edit_task_dialog_title
                                } else {
                                    R.string.add_task_dialog_title
                                }
                            ),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp),
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = todoViewModel.task,
                            onValueChange = {
                                if (it.length <= 50) {
                                    todoViewModel.updateTaskInput(it)
                                }
                            },
                            label = { Text(stringResource(id = R.string.task)) },
                            isError = isTaskError,
                            supportingText = {
                                if (isTaskError) {
                                    Text(
                                        stringResource(
                                            id = R.string.require,
                                            stringResource(id = R.string.task)
                                        )
                                    )
                                }
                            },
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = todoViewModel.notes,
                            onValueChange = {
                                if (it.length <= 100) {
                                    todoViewModel.updateNotesInput(it)
                                }
                            },
                            label = { Text(stringResource(id = R.string.notes)) },
                            isError = false,
                            supportingText = {
                                Text(
                                    text = "${todoViewModel.notes.length}/100",
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            TextButton(
                                onClick = { onDismissRequest() },
                                modifier = Modifier.padding(8.dp),
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
                            ) {
                                Text(
                                    text = stringResource(R.string.cancel),
                                    color = MaterialTheme.colorScheme.error,
                                )
                            }
                            Button(
                                onClick = {
                                    if (todoViewModel.task.isEmpty()) {
                                        isTaskError = true
                                    } else {
                                        val todo = TodoEntity(
                                            id = todoViewModel.id,
                                            task = todoViewModel.task,
                                            notes = todoViewModel.notes,
                                            status = false,
                                        )
                                        onUpsert.invoke(todo)
                                    }
                                },
                                modifier = Modifier.padding(8.dp),
                                shape = RoundedCornerShape(8.dp),
                            ) {
                                Text(
                                    stringResource(
                                        if (todoViewModel.id != 0) {
                                            R.string.update
                                        } else {
                                            R.string.save
                                        }
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}