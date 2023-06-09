package com.example.bahasaku.model.entity.remote

data class ProgressChapter(
    val available: MutableList<Boolean> = mutableListOf(),
    val progress: MutableList<Int> = mutableListOf()
)
