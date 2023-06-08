package com.example.bahasaku.ui.exercise.listexercisechapter

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BChapterCard
import com.example.bahasaku.core.components.BSnackbar
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.destinations.ListExerciseCardScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ListExerciseChapterScreen(
    navigator: DestinationsNavigator,
    viewModel: ListExerciseChapterViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    val state by viewModel.state.collectAsState()

    viewModel.getProgress()
    viewModel.getAllChapter()
    viewModel.updateScore()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                BTopAppBar(title = "Latihan")
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.ExerciseScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                ListExerciseChapterContent(
                    { chapter ->
                        navigator.navigate(ListExerciseCardScreenDestination(chapter))
                    },
                    {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Bab terkunci, silahkan selesaikan latihan bab sebelumnya"
                            )
                        }
                    },
                    state
                )
            }
            BSnackbar(padding = padding, snackbarHostState = snackbarHostState)
        }
    }

}

@Composable
fun ListExerciseChapterContent(
    navigateToCourse: (Chapter) -> Unit,
    showSnackbar: () -> Unit,
    data: ListExerciseChapterState
) {
    Log.d("Reditya", "ListExerciseChapterScreen ${data.progress}")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 8.dp),
            columns = GridCells.Fixed(2),
            content = {
                if (data.listChapter.isNotEmpty() && data.progress.available.size > 0) {
                    items(data.listChapter) {
                        BChapterCard(
                            chapterData = it,
                            isLearning = false,
                            navigateToCourse = navigateToCourse,
                            showSnackbar = showSnackbar,
                            isAvailable = data.progress.available[it.id.toInt()],
                            progress = data.progress.progress[it.id.toInt()]
                        )
                    }
                }

            }
        )
    }
}