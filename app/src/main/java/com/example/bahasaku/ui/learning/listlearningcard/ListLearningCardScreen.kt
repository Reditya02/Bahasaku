package com.example.bahasaku.ui.learning.listlearningcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
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
import com.example.bahasaku.destinations.LearningScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ListLearningCardScreen(
    navigator: DestinationsNavigator,
    chapter: Chapter,
    viewModel: ListLearningCardViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val id = chapter.id
    val title = chapter.title

    val state by viewModel.state.collectAsState()

    viewModel.updateChapterProgress(id)
    viewModel.getAllCard(id)
    viewModel.getProgress(id)

    Surface {
        Column {
            ListLearningCardContent(
                onBackPressed = { navigator.popBackStack() },
                navigateToCourseContent = { i ->
                    navigator.navigate(LearningScreenDestination(chapter, i))
                },
                showSnackbar = {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Materi terkunci, silahkan pelajari materi sebelumnya"
                        )
                    }
                },
                snackbarHostState = snackbarHostState,
                title = title,
                state = state,
            )
        }
    }
}

@Composable
fun ListLearningCardContent(
    onBackPressed: () -> Unit,
    navigateToCourseContent: (Int) -> Unit,
    showSnackbar: () -> Unit,
    snackbarHostState: SnackbarHostState,
    title: String,
    state: ListCardLearningState,
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
                                onCardClicked = { _, _ -> navigateToCourseContent(i) },
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