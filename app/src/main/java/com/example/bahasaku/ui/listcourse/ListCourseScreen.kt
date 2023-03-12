package com.example.bahasaku.ui.listcourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bahasaku.core.components.BCourseCard
import com.example.bahasaku.core.components.CourseType
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.data.ChapterData
import com.example.bahasaku.data.CourseData
import com.example.bahasaku.ui.destinations.ExerciseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ListCourseScreen(
    navigator: DestinationsNavigator,
    data: ChapterData
) {
    Surface {
        Column {
            ListCourseContent(
                { navigator.popBackStack() },
                { navigator.navigate(ExerciseScreenDestination) },
                data
            )
        }
    }
}

@Composable
fun ListCourseContent(
    onBackPressed: () -> Unit,
    onCourseClicked: () -> Unit,
    chapterData: ChapterData
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = chapterData.title) },
                Modifier.background(MaterialTheme.colors.background),
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            LazyColumn(
                content = {
                    items(CourseData.getListDummy) {
                        BCourseCard(
                            Modifier.fillMaxWidth(),
                            name = it.name,
                            type = it.type,
                            isAvailable = it.isAvailable,
                            isDone = it.isDone,
                            onClick = onCourseClicked
                        )
                    }
                }
            )
        }
    }
}
//
//@Preview
//@Composable
//fun ListCoursePreview() {
//    BahasakuTheme {
//        Surface {
//            Column {
//                ListCourseContent({}, {})
//            }
//        }
//    }
//}