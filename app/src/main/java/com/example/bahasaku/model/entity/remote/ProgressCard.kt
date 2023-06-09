package com.example.bahasaku.model.entity.remote

data class ProgressCard(
    val available: MutableList<Boolean> = mutableListOf(),
    val done: MutableList<Boolean> = mutableListOf()
)
