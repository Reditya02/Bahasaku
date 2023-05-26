package com.example.bahasaku.ui.detailcard

import com.example.bahasaku.data.model.Word

data class DetailCardState(
    val child: Word = Word(),

    val listChild: List<Word> = listOf(),

    val listWord: List<Word> = listOf()
)