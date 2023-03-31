package com.example.bahasaku.ui.exercise

import com.example.bahasaku.data.model.Question

data class ExerciseState(
    val answer: String = "",
    val question: List<Question> = listOf(),
    val position: Int = 0,
    val exerciseCondition: List<ExerciseCondition> = listOf()
)
