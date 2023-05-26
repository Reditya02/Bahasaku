package com.example.bahasaku.ui.listexercisechapter

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
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.destinations.ListExerciseScreenDestination
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

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Latihan") },
                    Modifier.background(MaterialTheme.colors.background)
                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.ExerciseScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                viewModel.getProgress()
                viewModel.getAllChapter()
                ListExerciseChapterContent(
                    { id, title ->
                        navigator.navigate(ListExerciseScreenDestination(id, title))
                    },
                    snackbarHostState,
                    {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Bab terkunci, silahkan selesaikan bab sebelumnya"
                            )
                        }
                    },
                    state
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1F))
                SnackbarHost(
                    modifier = Modifier.padding(padding),
                    hostState = snackbarHostState,
                    snackbar = {
                        snackbarHostState.currentSnackbarData?.let {
                            Snackbar(
                                it,
//                                backgroundColor = MaterialTheme.colors.background,
//                                contentColor = MaterialTheme.colors.onBackground,
                                elevation = 16.dp
                            )
                        }
                    }
                )
            }
        }
    }

}

@Composable
fun ListExerciseChapterContent(
    navigateToCourse: (String, String) -> Unit,
    snackbarHostState: SnackbarHostState,
    showSnackbar: () -> Unit,
    data: ListExerciseChapterState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 8.dp),
            columns = GridCells.Fixed(2),
            content = {
                Log.d("Reditya", data.toString())
                if (data.listChapter.isNotEmpty() && data.progress.available.size > 0) {
                    items(data.listChapter) {
                        BChapterCard(
                            chapterData = it,
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