package com.example.bahasaku.ui.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.bahasaku.core.components.BBottomNavigationBar
import com.example.bahasaku.core.components.BCardWithProgress
import com.example.bahasaku.core.components.BLeaderboardItem
import com.example.bahasaku.core.navigation.BottomNavigationDestination
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            content = {
                items(dummy) {
                    if (it.rank != "1") {
                        Divider(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clip(CircleShape),
                            color = MaterialTheme.colors.onBackground,
                            thickness = 1.dp
                        )
                    }
                    BLeaderboardItem(
                        rank = it.rank,
                        photoUrl = it.photoUrl,
                        name = it.name,
                        score = it.score
                    )
                }
            }
        )
    }
}

val dummy: List<LeaderboardData>
    get() {
        val data = mutableListOf<LeaderboardData>()
        for (i in 0..10) {
            data.add(
                LeaderboardData(
                    rank = "${i+1}",
                    photoUrl = "https://loremflickr.com/320/240",
                    name = "John Doe $i",
                    score = "${100 - i}"
                )
            )
        }
        return data
    }