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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BCourseCard
import com.example.bahasaku.core.components.CourseType
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.destinations.ExerciseScreenDestination
import com.example.bahasaku.destinations.ReadingScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ListCourseScreen(
    navigator: DestinationsNavigator,
    id: String,
    title: String,
    viewModel: ListCourseViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    val state by viewModel.state.collectAsState()

    viewModel.getAllCourse(id)

    Surface {
        Column {
            ListCourseContent(
                { navigator.popBackStack() },
                {
                    if (it.type == CourseType.Exercise)
                        navigator.navigate(ExerciseScreenDestination(it))
                    else
                        navigator.navigate(ReadingScreenDestination())
                },
                {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Materi terkunci, silahkan selesaikan materi dan latihan soal sebelumnya"
                        )
                    }
                },
                snackbarHostState,
                title,
                state
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
    title: String,
    courseData: ListCourseState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title) },
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
                    items(courseData.listCourse) {
                        BCourseCard(
                            Modifier.fillMaxWidth(),
                            courseData = it,
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