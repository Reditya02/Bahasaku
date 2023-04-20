package com.example.bahasaku.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bahasaku.destinations.DictionaryScreenDestination
import com.example.bahasaku.destinations.HomeScreenDestination
import com.example.bahasaku.destinations.LeaderboardScreenDestination
import com.example.bahasaku.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomNavigationDestination(
    val direction: DirectionDestinationSpec,
    val text: String,
    val icon: ImageVector
) {
    HomeScreen(HomeScreenDestination, "Home", Icons.Default.Home),
    DictionaryScreen(DictionaryScreenDestination, "Dictionary", Icons.Default.Book),
    LeaderboardScreen(LeaderboardScreenDestination, "Leaderboard", Icons.Default.Leaderboard),
    ProfileScreen(ProfileScreenDestination, "Profile", Icons.Default.Person)
}