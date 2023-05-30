package com.example.bahasaku.ui.listexercisecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BWordCard
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.Word
import com.example.bahasaku.destinations.ExerciseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ListExerciseCardScreen(
    navigator: DestinationsNavigator,
    chapter: Chapter,
    viewModel: ListExerciseCardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val snackbarHostState = SnackbarHostState()
    val id = chapter.id
    val title = chapter.title

    if (chapter.chapterChild != "") {
        viewModel.getAllCardWithChild(id, chapter.chapterChild)
        viewModel.getProgressWithChild(id, chapter.chapterChild)
    } else {
        viewModel.getAllCard(id)
        viewModel.getProgress(id)
    }

    viewModel.updateChapterProgress(id)

    if (state.progress.done.size == state.listWord.size)
        viewModel.updateChapterAvailable(chapter.id)

    Surface {
        Column {
            ListExerciseCardContent(
                onBackPressed = { navigator.popBackStack() },
                onCardClicked = {
                    navigator.navigate(ExerciseScreenDestination(it))
                },
                showSnackbar = { /*TODO*/ },
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
        Column(Modifier.padding(padding)) {
            Spacer(modifier = Modifier.weight(1F))
            SnackbarHost(
                modifier = Modifier.padding(padding),
                hostState = snackbarHostState
            )
        }
    }
}