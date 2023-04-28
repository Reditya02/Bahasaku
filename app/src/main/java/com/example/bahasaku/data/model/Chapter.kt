package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Chapter(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    val courseNumber: Int = 0,
    val imageUrl: String = "",
    val chapterChild: String = "",
    val isChild: Boolean = false
) : Parcelable {
    companion object {
        val getListDummy: List<Chapter>
            get() {
                val data = mutableListOf<Chapter>()
                for (i in 0..10) {
                    data.add(
                        Chapter(
                            title = "Bab $i",
                            courseNumber = i,
                            imageUrl = "Hewan/anak_bebek.png"
                        )
                    )
                }
                return data
            }
    }
}
