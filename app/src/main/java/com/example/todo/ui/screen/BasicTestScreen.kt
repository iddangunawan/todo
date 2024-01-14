package com.example.todo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun BasicTestScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Test 1 | listOf(1, 3, 5, 7, 9)",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "Answer: ${miniMaxSum(listOf(1, 3, 5, 7, 9))}",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Test 2 | listOf(1, 1, 0, -1, -1)",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "Answer: ${plusMinus(listOf(1, 1, 0, -1, -1))}",
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Test 3",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

fun miniMaxSum(arr: List<Int>): String {
    // Sorting the list in ascending order
    val sortedArr = arr.sorted()

    // Finding the minimum sum
    val minSum = sortedArr.take(4).sum()

    // Finding the maximum sum
    val maxSum = sortedArr.takeLast(4).sum()

    return "$minSum $maxSum"
}

fun plusMinus(arr: List<Int>): String {
    // Initialization of counts for positive, negative, and zero values
    var positiveCount = 0
    var negativeCount = 0
    var zeroCount = 0

    // Check whether the array elements are positive, negative, or zero
    for (num in arr) {
        when {
            num > 0 -> positiveCount++
            num < 0 -> negativeCount++
            else -> zeroCount++
        }
    }

    // Calculate the total number of elements in the array
    val total = arr.size.toDouble()

    // Calculate the ratio of positive, negative, and zero values to the total number of elements
    val positiveRatio = positiveCount / total
    val negativeRatio = negativeCount / total
    val zeroRatio = zeroCount / total

    // Print the ratios with a format of six decimal places
    return "\n %.6f".format(positiveRatio) +
            "\n %.6f".format(negativeRatio) +
            "\n %.6f".format(zeroRatio)
}