package com.example.bahasaku.ui.exercise.listexercisecard

import com.example.bahasaku.model.entity.Word
import com.example.bahasaku.model.entity.remote.ProgressCard

data class ListExerciseCardState(
    val listWord: List<Word> = listOf(),
    val progress: ProgressCard = ProgressCard(
        done = mutableListOf(true),
        available = mutableListOf()
    )
)
