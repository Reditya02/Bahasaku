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
    var answer: String = "",
    var option: String= ""
) : Parcelable {

    init {
        if (type == QuestionType.Option)
            answer = option.split("$")[0]
    }

    fun getOption(): List<String> {
        return option.split("$")
    }

    companion object {
        val getDummyEssay = Question(
            question = "Pertanyaan",
            type = QuestionType.Essay,
            answer = "Benar"
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
