package com.example.bahasaku.ui.exercise.listexercisechapter

import com.example.bahasaku.model.entity.Chapter
import com.example.bahasaku.model.entity.remote.ProgressChapter

data class ListExerciseChapterState(
    val listChapter: List<Chapter> = emptyList(),
    val progress: ProgressChapter = ProgressChapter(
        available = mutableListOf(),
        progress = mutableListOf()
    )
)
