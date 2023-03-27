package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Question private constructor(
    val id: String = "",
    val courseId: String = "",
    val question: String,
    val type: QuestionType,
    val answerEssay: String = "",
    val option: List<String> = listOf()
) : Parcelable {

    constructor(
        id: String, courseId: String, question: String, type: QuestionType, answerEssay: String
    ) : this(
        id, courseId, question, type, answerEssay, listOf()
    )

    constructor(
        id: String, courseId: String, question: String, type: QuestionType, option: List<String>
    ) : this(
        id, courseId, question, type, option[0], option
    )

    companion object {
        val getDummyEssay = Question(
            question = "Pertanyaan",
            type = QuestionType.Essay,
            answerEssay = "Benar"
        )

        val getDummyOption = Question(
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
