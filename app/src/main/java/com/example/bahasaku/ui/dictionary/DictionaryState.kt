package com.example.bahasaku.ui.dictionary

import com.example.bahasaku.model.entity.Word

data class DictionaryState(
    val query: String = "",

    val listWord: List<Word> = listOf()
)
