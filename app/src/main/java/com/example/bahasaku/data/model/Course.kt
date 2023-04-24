package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bahasaku.core.components.CourseType
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Course(
    @PrimaryKey
    val id: String = "",
    val chapterId: String = "",
    val name: String,
    val imageUrl: String = "Hewan/anak_bebek.png",
    val type: CourseType,
    var isAvailable: Boolean = false,
    var isDone: Boolean = false,
    var score: Int = 0,
    val reading: String = ""
) : Parcelable {
    fun getReadingList(): List<String> {
        return reading.split("$")
    }

    companion object {
        val getListDummy: List<Course>
            get() {
                val data = mutableListOf<Course>()
                for (i in 0..14) {
                    val type = if (i % 3 == 1) CourseType.Exercise else CourseType.Reading
                    data.add(
                        Course(
                            name = "Course $i $type",
                            type = type,
                            isAvailable = i <= 10,
                            isDone = i < 6,
                            imageUrl = "Hewan/anak_bebek.png"
                        )
                    )
                }
                return data
            }
    }
}