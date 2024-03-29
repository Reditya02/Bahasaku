package com.example.bahasaku.ui.exercise.listexercisecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BSnackbar
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.core.components.BWordCard
import com.example.bahasaku.model.entity.Chapter
import com.example.bahasaku.model.entity.Word
import com.example.bahasaku.destinations.ExerciseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ListExerciseCardScreen(
    navigator: DestinationsNavigator,
    chapter: Chapter,
    viewModel: ListExerciseCardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val id = chapter.id
    val title = chapter.title

    if (chapter.chapterChild != "") {
        viewModel.getAllCardWithChild(id, chapter.chapterChild)
        viewModel.getProgressWithChild(id, chapter.chapterChild)
    } else {
        viewModel.getAllCard(id)
        viewModel.getProgress(id)
    }

    viewModel.updateChapterProgress(id, chapter.chapterChild)

    if (state.progress.done.count { it } == state.listWord.size)
        viewModel.updateChapterAvailable(chapter.id)

    Surface {
        Column {
            ListExerciseCardContent(
                onBackPressed = { navigator.popBackStack() },
                onCardClicked = {
                    navigator.navigate(ExerciseScreenDestination(it, chapter.title))
                },
                showSnackbar = {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Latihan terkunci, silahkan selesaikan materi terlebih dahulu"
                        )
                    }
                },
                snackbarHostState = snackbarHostState,
                title = title,
                state = state
            )
        }
    }
}

@Composable
fun ListExerciseCardContent(
    onBackPressed: () -> Unit,
    onCardClicked: (Word) -> Unit,
    showSnackbar: () -> Unit,
    snackbarHostState: SnackbarHostState,
    title: String,
    state: ListExerciseCardState,
) {
    Scaffold(
        topBar = {
            BTopAppBar(title = title, hasBackButton = true, onBackPressed = onBackPressed)
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 8.dp),
                columns = GridCells.Fixed(4),
                content = {
                    if (state.listWord.isNotEmpty() && state.progress.available.size > 0) {
                        itemsIndexed(state.listWord) { i, it ->
                            BWordCard(
                                Modifier.fillMaxWidth(),
                                onCardClicked = { _, _ -> onCardClicked(it) },
                                showSnackbar = showSnackbar,
                                word = it,
                                isAvailable = state.progress.available[i],
                                isDone = state.progress.done[i],
                            )
                        }
                    }
                }
            )
        }
        BSnackbar(padding = padding, snackbarHostState = snackbarHostState)
    }
}