package com.example.bahasaku.data.model.remote

data class ProgressChapter(
    val available: MutableList<Boolean> = mutableListOf(),
    val progress: MutableList<Int> = mutableListOf()
)
