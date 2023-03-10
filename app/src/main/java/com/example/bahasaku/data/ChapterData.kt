package com.example.bahasaku.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterData(
    val title: String,
    val progress: Float
) : Parcelable {
    companion object {
        val getListDummy: List<ChapterData>
            get() {
                val data = mutableListOf<ChapterData>()
                for (i in 0..10) {
                    data.add(
                        ChapterData(
                            title = "Bab $i",
                            progress = i.toFloat() / 10
                        )
                    )
                }
                return data
            }
    }
}
