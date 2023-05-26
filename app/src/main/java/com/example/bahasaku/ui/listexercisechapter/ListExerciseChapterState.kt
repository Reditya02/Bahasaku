package com.example.bahasaku.ui.listexercisechapter

import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.remote.ProgressChapter

data class ListExerciseChapterState(
    val listChapter: List<Chapter> = emptyList(),
    val progress: ProgressChapter = ProgressChapter(
        available = mutableListOf(),
        progress = mutableListOf()
    )
)
