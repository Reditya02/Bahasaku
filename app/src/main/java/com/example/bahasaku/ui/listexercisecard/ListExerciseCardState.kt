package com.example.bahasaku.ui.listexercisecard

import com.example.bahasaku.data.model.Word
import com.example.bahasaku.data.model.remote.ProgressCard

data class ListExerciseCardState(
    val listWord: List<Word> = listOf(),
    val progress: ProgressCard = ProgressCard(
        done = mutableListOf(true),
        available = mutableListOf()
    )
)
