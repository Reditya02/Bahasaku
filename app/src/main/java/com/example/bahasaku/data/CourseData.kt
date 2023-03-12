package com.example.bahasaku.data

import android.os.Parcelable
import com.example.bahasaku.core.components.CourseType
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseData(
    val id: Int = 0,
    val chapterId: Int = 0,
    val name: String,
    val type: CourseType,
    var isAvailable: Boolean = false,
    var isDone: Boolean = false,
    var score: Int = 0
) : Parcelable {
    companion object {
        val getListDummy: List<CourseData>
            get() {
                val data = mutableListOf<CourseData>()
                for (i in 0..14) {
                    val type = if (i % 3 == 1) CourseType.Exercise else CourseType.Reading
                    data.add(
                        CourseData(
                            name = "Course $i $type",
                            type = type,
                            isAvailable = i <= 10,
                            isDone = i < 6
                        )
                    )
                }
                return data
            }
    }
}