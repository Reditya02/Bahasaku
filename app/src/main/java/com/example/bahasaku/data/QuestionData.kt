package com.example.bahasaku.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionData private constructor(
    val question: String,
    val type: QuestionType,
    val answerEssay: String = "",
    val option: List<String> = listOf()
) : Parcelable {

    constructor(
        question: String,
        type: QuestionType,
        answerEssay: String
    ) : this(
        question,
        type,
        answerEssay,
        listOf()
    )

    constructor(
        question: String,
        type: QuestionType,
        option: List<String>
    ) : this(
        question,
        type,
        option[0],
        option
    )

}

enum class QuestionType {
    Essay, Option
}
