package com.example.bahasaku.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chapter(
    @PrimaryKey
    val id: String = "",
    val title: String,
    val courseNumber: Int,
    val imageUrl: String = ""
) {
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
