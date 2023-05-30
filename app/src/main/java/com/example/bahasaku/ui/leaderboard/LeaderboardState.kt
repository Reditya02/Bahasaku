package com.example.bahasaku.ui.leaderboard

import com.example.bahasaku.data.model.User

data class LeaderboardState(
    val listUser: List<User> = listOf(),
    val currentUser: User = User()
)
