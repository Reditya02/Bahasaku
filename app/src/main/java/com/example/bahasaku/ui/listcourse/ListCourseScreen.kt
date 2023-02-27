package com.example.bahasaku.ui.listcourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import com.example.bahasaku.ui.home.dummy
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
                        Row(
                            Modifier.fillMaxWidth()
                        ) {
                            BCourseCard(
                                Modifier.weight(1f),
                                name = it.title,
                                type = CourseType.Exercise,
                                isAvailable = it.progress > 0.5
                            )
                            if (it.progress < 0.7) {
                                BCourseCard(
                                    Modifier.weight(1f),
                                    name = "latihan ${it.title}",
                                    type = CourseType.Exercise,
                                    isAvailable = it.progress > 0.5
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
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