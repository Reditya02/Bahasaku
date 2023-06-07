package com.example.bahasaku.ui.learning.learningpage

import com.example.bahasaku.data.model.Word

data class LearningState(
    val child: Word = Word(),

    val listChild: List<Word> = listOf(),

    val listWord: List<Word> = listOf()
)