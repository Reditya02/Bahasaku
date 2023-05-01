package com.example.bahasaku.ui.listcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BWordCard
import com.example.bahasaku.data.model.Course
import com.example.bahasaku.data.model.Word
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun ListCardScreen(
    navigator: DestinationsNavigator,
    id: String,
    title: String,
    viewModel: ListCardViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    val state by viewModel.state.collectAsState()

    viewModel.getAllCard(id)
    viewModel.updateProgress(id)

    Surface {
        Column {
            ListCardContent(
                onBackPressed = { navigator.popBackStack() },
                navigateToCourseContent = {  },
                showSnackbar = {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Materi terkunci, silahkan selesaikan materi dan latihan soal sebelumnya"
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
fun ListCardContent(
    onBackPressed: () -> Unit,
    navigateToCourseContent: (Course) -> Unit,
    showSnackbar: () -> Unit,
    snackbarHostState: SnackbarHostState,
    title: String,
    state: ListCardState
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
                        itemsIndexed(state.listWord) {i, it ->
                            BWordCard(
                                Modifier.fillMaxWidth(),
                                navigateToCourseContent = navigateToCourseContent,
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