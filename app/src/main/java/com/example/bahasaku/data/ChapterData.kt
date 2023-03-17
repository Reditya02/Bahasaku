package com.example.bahasaku.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterData(
    val id: Int = 0,
    val title: String,
    var progress: Float,
    var isAvailable: Boolean = false,
    var courses: MutableList<CourseData> = mutableListOf()
) : Parcelable {
    companion object {
        val getListDummy: List<ChapterData>
            get() {
                val data = mutableListOf<ChapterData>()
                for (i in 0..10) {
                    data.add(
                        ChapterData(
                            title = "Bab $i",
                            progress = (10 - i.toFloat()) / 10,
                            isAvailable = i != 10
                        )
                    )
                }
                return data
            }
    }
}
