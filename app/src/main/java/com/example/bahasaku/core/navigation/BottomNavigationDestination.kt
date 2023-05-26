package com.example.bahasaku.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bahasaku.destinations.*
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomNavigationDestination(
    val direction: DirectionDestinationSpec,
    val text: String,
    val icon: ImageVector
) {
    LearningScreen(ListLearningChapterScreenDestination, "Learning", Icons.Default.Home),
    ExerciseScreen(ListExerciseChapterScreenDestination, "Exercise", Icons.Default.CheckBox),
    DictionaryScreen(DictionaryScreenDestination, "Dictionary", Icons.Default.Book),
    LeaderboardScreen(LeaderboardScreenDestination, "Leaderboard", Icons.Default.Leaderboard),
    ProfileScreen(ProfileScreenDestination, "Profile", Icons.Default.Person)
}