package com.example.bahasaku.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bahasaku.ui.destinations.DictionaryScreenDestination
import com.example.bahasaku.ui.destinations.HomeScreenDestination
import com.example.bahasaku.ui.destinations.LeaderboardScreenDestination
import com.example.bahasaku.ui.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomNavigationDestination(
    val direction: DirectionDestinationSpec,
    val text: String,
    val icon: ImageVector
) {
    HomeScreen(HomeScreenDestination, "Home", Icons.Default.Lock),
    DictionaryScreen(DictionaryScreenDestination, "Dictionary", Icons.Default.Lock),
    LeaderboardScreen(LeaderboardScreenDestination, "Leaderboard", Icons.Default.Lock),
    ProfileScreen(ProfileScreenDestination, "Profile", Icons.Default.Lock)
}