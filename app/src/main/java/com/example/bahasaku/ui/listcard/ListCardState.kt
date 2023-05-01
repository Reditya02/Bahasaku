package com.example.bahasaku.ui.listcard

import com.example.bahasaku.data.model.Word
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter

data class ListCardState(
    val listWord: List<Word> = listOf(),
    val progress: ProgressCard = ProgressCard(
        done = mutableListOf(),
        available = mutableListOf()
    )
)
