package com.example.bahasaku.ui.leaderboard

import android.transition.Fade
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BCardWithProgress
import com.example.bahasaku.core.components.BLeaderboardCard
import com.example.bahasaku.core.components.BLeaderboardItem
import com.example.bahasaku.core.navigation.BottomNavigationDestination
import com.example.bahasaku.core.theme.BahasakuTheme
import com.example.bahasaku.data.LeaderboardData
import com.example.bahasaku.data.LessonData
import com.example.bahasaku.ui.home.HomeContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LeaderboardScreen(
    navigator: DestinationsNavigator,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Papan Peringkat") },
                    Modifier.background(MaterialTheme.colors.background)
                )
            },
            bottomBar = {
                BBottomNavigationBar(
                    selected = BottomNavigationDestination.LeaderboardScreen,
                    navigator = navigator
                )
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                LeaderboardContent()
            }
        }
    }
}

@Composable
fun LeaderboardContent() {
    val scrollState = rememberLazyListState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            LazyColumn(
                state = scrollState,
                content = {
                    items(dummy) {
                        if (it.rank != 1) {
                            Divider(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .clip(CircleShape),
                                color = MaterialTheme.colors.onBackground,
                                thickness = 1.dp
                            )
                        }
                        BLeaderboardItem(
                            rank = it.rank.toString(),
                            photoUrl = it.photoUrl,
                            name = it.name,
                            score = it.score
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
                    rank = "1",
                    photoUrl = "https://loremflickr.com/320/240",
                    name = "John Doe",
                    score = "14045"
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

@Preview
@Composable
fun LeaderBoardScreenPreview() {
    BahasakuTheme {
        Surface {
            LeaderboardContent()
        }
    }
}

val dummy: List<LeaderboardData>
    get() {
        val data = mutableListOf<LeaderboardData>()
        for (i in 0..10) {
            data.add(
                LeaderboardData(
                    rank = i+1,
                    photoUrl = "https://loremflickr.com/320/240",
                    name = "John Doe $i",
                    score = "${100 - i}"
                )
            )
        }
        return data
    }