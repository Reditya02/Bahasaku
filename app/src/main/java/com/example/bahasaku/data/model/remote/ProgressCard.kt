package com.example.bahasaku.data.model.remote

data class ProgressCard(
    val available: MutableList<Boolean> = mutableListOf(),
    val done: MutableList<Boolean> = mutableListOf()
)
