package com.example.bahasaku.ui.leaderboard

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BLeaderboardCard
import com.example.bahasaku.core.components.BLeaderboardItem
import com.example.bahasaku.core.components.BTopAppBar
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.model.entity.User
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LeaderboardScreen(
    navigator: DestinationsNavigator,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    viewModel.getLeaderboard()
    viewModel.getCurrentUser()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                BTopAppBar(title = "Papan Peringkat")
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.LeaderboardScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                LeaderboardContent(
                    state.listUser,
                    state.currentUser
                )
            }
        }
    }
}

@Composable
fun LeaderboardContent(
    listUser: List<User>,
    currentUser: User
) {
    val scrollState = rememberLazyListState()

    Log.d("Reditya", "Current $currentUser")

    var rank by remember {
        mutableStateOf(0)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = scrollState,
                content = {
                    itemsIndexed(listUser) { i, it ->
                        if (it.email == currentUser.email)
                            rank = i + 1
                        if (i != 0) {
                            Divider(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .clip(CircleShape),
                                color = MaterialTheme.colors.onBackground,
                                thickness = 1.dp
                            )
                        }
                        BLeaderboardItem(
                            rank = (i + 1).toString(),
                            photoUrl = it.image,
                            name = it.name,
                            score = it.score.toString()
                        )
                    }
                }
            )
            androidx.compose.animation.AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = scrollState.isScrollingUp(),
                enter = slideInVertically(
                    initialOffsetY = {
                        it / 2
                    }
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    }
                ) + fadeOut()
            ) {
                BLeaderboardCard(
                    rank = rank.toString(),
                    photoUrl = currentUser.image,
                    name = currentUser.name,
                    score = currentUser.score.toString()
                )
            }
        }

    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}