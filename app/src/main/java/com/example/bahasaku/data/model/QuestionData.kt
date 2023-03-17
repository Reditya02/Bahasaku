package com.example.bahasaku.data.model

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
        question: String, type: QuestionType, answerEssay: String
    ) : this(
        question, type, answerEssay, listOf()
    )

    constructor(
        question: String, type: QuestionType, option: List<String>
    ) : this(
        question, type, option[0], option
    )

    companion object {
        val getDummyEssay = QuestionData(
            question = "Pertanyaan",
            type = QuestionType.Essay,
            answerEssay = "Benar"
        )

        val getDummyOption = QuestionData(
            question = "Pertanyaan",
            type = QuestionType.Option,
            option = listOf(
                "Benar",
                "Salah 1",
                "Salah 2",
                "Salah 3",
            )
        )
    }

}

enum class QuestionType {
    Essay, Option
}
