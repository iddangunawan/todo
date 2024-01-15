package com.example.todo

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.example.todo.data.database.entity.TodoEntity
import com.example.todo.ui.base.ShowWarning
import com.example.todo.ui.components.TodoLayout
import org.junit.Rule
import org.junit.Test

class TodoScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun todolist_whenTodoLayout_isShown() {
        composeTestRule.setContent {
            TodoLayout(
                todoList = testTodoList,
                todoClicked = {},
                todoClickedDelete = {},
                deleteTodo = {},
            )
        }

        composeTestRule
            .onNodeWithText(
                testTodoList[0].task!!,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    testTodoList[1].task!!,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                testTodoList[2].task!!,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun error_whenShowError_isShown() {
        val errorMessage = "Error Message For You"

        composeTestRule.setContent {
            ShowWarning(
                text = errorMessage
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}

private val testTodoList = listOf(
    TodoEntity(
        id = 1,
        task = "Task 1",
        notes = "Notes 1",
        status = false,
    ),
    TodoEntity(
        id = 2,
        task = "Task 2",
        notes = "Notes 2",
        status = false,
    ),
    TodoEntity(
        id = 3,
        task = "Task 3",
        notes = "Notes 3",
        status = false,
    ),
)