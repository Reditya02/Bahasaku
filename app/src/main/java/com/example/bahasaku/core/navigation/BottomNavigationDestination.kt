package com.example.bahasaku.core.navigation

import com.example.bahasaku.R
import com.example.bahasaku.destinations.*
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomNavigationDestination(
    val direction: DirectionDestinationSpec,
    val text: String,
    val icon: Int
) {
    LearningScreen(ListLearningChapterScreenDestination, "Learning", R.drawable.learn),
    ExerciseScreen(ListExerciseChapterScreenDestination, "Exercise", R.drawable.exercise),
    DictionaryScreen(DictionaryScreenDestination, "Dictionary", R.drawable.dictionary),
    LeaderboardScreen(LeaderboardScreenDestination, "Leaderboard", R.drawable.leaderboard),
    ProfileScreen(ProfileScreenDestination, "Profile", R.drawable.profile)
}