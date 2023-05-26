package com.example.bahasaku.ui.listlearningcard

import com.example.bahasaku.data.model.Word
import com.example.bahasaku.data.model.remote.ProgressCard

data class ListCardLearningState(
    val listWord: List<Word> = listOf(),
    val progress: ProgressCard = ProgressCard(
        done = mutableListOf(),
        available = mutableListOf()
    )
)
