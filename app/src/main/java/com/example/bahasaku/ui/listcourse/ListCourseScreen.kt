package com.example.bahasaku.ui.listcourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bahasaku.core.components.BCourseCard
import com.example.bahasaku.core.components.CourseType
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.data.CourseData
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ListCourseScreen(
    navigator: DestinationsNavigator
) {
    Surface {
        Column {
            ListCourseContent()
        }
    }
}

@Composable
fun ListCourseContent() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "List Course") },
                Modifier.background(MaterialTheme.colors.background)
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            LazyColumn(
                content = {
                    items(dummy) {
                        BCourseCard(
                            Modifier.fillMaxWidth(),
                            name = it.name,
                            type = it.type,
                            isAvailable = it.isAvailable,
                            isDone = it.isDone
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ListCoursePreview() {
    BahasakuTheme {
        Surface {
            Column {
                ListCourseContent()
            }
        }
    }
}

val dummy: List<CourseData>
    get() {
        val data = mutableListOf<CourseData>()
        for (i in 0..14) {
            val type = if (i % 3 == 1) CourseType.Exercise else CourseType.Reading
            data.add(
                CourseData(
                    name = "Course $i $type",
                    type = type,
                    isAvailable = i <= 10,
                    isDone = i < 6
                )
            )
        }
        return data
    }