package com.example.bahasaku.ui.learning.listlearningcard

import com.example.bahasaku.model.entity.Word
import com.example.bahasaku.model.entity.remote.ProgressCard

data class ListCardLearningState(
    val listWord: List<Word> = listOf(),
    val progress: ProgressCard = ProgressCard(
        done = mutableListOf(),
        available = mutableListOf()
    )
)
