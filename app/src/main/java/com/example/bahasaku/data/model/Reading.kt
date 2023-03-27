package com.example.bahasaku.data.model

data class Reading(
    val id: String = "",
    val courseId: String = "",
    var material: List<String> = listOf()
)
