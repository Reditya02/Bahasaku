package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Question constructor(
    @PrimaryKey
    val id: String = "",
    val courseId: String = "",
    val question: String,
    val type: QuestionType,
    val answerEssay: String = "",
    var option: String= ""
) : Parcelable {

    constructor(
        id: String, courseId: String, question: String, type: QuestionType, answerEssay: String
    ) : this(
        id, courseId, question, type, answerEssay, ""
    )

    constructor(
        id: String, courseId: String, question: String, type: QuestionType, option: List<String>
    ) : this(
        id, courseId, question, type, option[0], option.toString()
    )

    companion object {
        val getDummyEssay = Question(
            question = "Pertanyaan",
            type = QuestionType.Essay,
            answerEssay = "Benar"
        )

//        val getDummyOption = Question(
//            question = "Pertanyaan",
//            type = QuestionType.Option,
//            option = listOf(
//                "Benar",
//                "Salah 1",
//                "Salah 2",
//                "Salah 3",
//            )
//        )
    }

}

enum class QuestionType {
    Essay, Option
}
