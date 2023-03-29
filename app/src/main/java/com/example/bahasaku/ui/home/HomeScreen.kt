package com.example.bahasaku.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.bahasaku.core.components.BCardWithProgress
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.ui.destinations.ListCourseScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel:HomeViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    val state by viewModel.state.collectAsState()
    Log.d("Reditya", "inside Screen ${state.listChapter}")


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Beranda") },
                    Modifier.background(MaterialTheme.colors.background)
                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.HomeScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                HomeContent(
                    { navigator.navigate(ListCourseScreenDestination(it)) },
                    snackbarHostState,
                    {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Bab terkunci, silahkan selesaikan bab sebelumnya"
                            )
                        }
                    },
                    state.listChapter
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
fun HomeContent(
    navigateToCourse: (Chapter) -> Unit,
    snackbarHostState: SnackbarHostState,
    showSnackbar: () -> Unit,
    data: List<Chapter>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            content = {
                items(data) {
                    BCardWithProgress(
                        data = it,
                        navigateToCourse = navigateToCourse,
                        showSnackbar = showSnackbar,
                    )
                }
            }
        )
    }
//    SnackbarHost(hostState = snackbarHostState)
}
