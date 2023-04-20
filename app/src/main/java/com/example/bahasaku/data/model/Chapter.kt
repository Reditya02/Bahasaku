package com.example.bahasaku.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class Chapter(
    @PrimaryKey
    val id: String = "",
    val title: String,
    val courseNumber: Int,
    val done: Int = 0,
    val isAvailable: Boolean = false,
    val imageUrl: String
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
                            done = Random.nextInt(0, i),
                            isAvailable = i != 10,
                            imageUrl = "Hewan/anak_bebek.png"
                        )
                    )
                }
                return data
            }
    }
}
