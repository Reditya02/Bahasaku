package com.example.bahasaku.ui.listcourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.bahasaku.core.components.BCourseCard
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.ui.destinations.ExerciseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ListCourseScreen(
    navigator: DestinationsNavigator,
    data: Chapter
) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    Surface {
        Column {
            ListCourseContent(
                { navigator.popBackStack() },
                { navigator.navigate(ExerciseScreenDestination(it)) },
                {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Materi terkunci, silahkan selesaikan materi dan latihan soal sebelumnya"
                        )
                    }
                },
                snackbarHostState,
                data
            )
        }
    }
}

@Composable
fun ListCourseContent(
    onBackPressed: () -> Unit,
    navigateToCourseContent: (Course) -> Unit,
    showSnackbar: () -> Unit,
    snackbarHostState: SnackbarHostState,
    chapter: Chapter
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = chapter.title) },
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
                    items(Course.getListDummy) {
                        BCourseCard(
                            Modifier.fillMaxWidth(),
                            data = it,
                            navigateToCourseContent = navigateToCourseContent,
                            showSnackbar = showSnackbar,

                        )
                    }
                }
            )
        }
        Column(Modifier.padding(padding)) {
            Spacer(modifier = Modifier.weight(1F))
            SnackbarHost(
                modifier = Modifier.padding(padding),
                hostState = snackbarHostState
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