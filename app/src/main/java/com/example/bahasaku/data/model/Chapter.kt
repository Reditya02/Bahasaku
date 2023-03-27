package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Chapter(
    val id: String = "",
    val title: String,
    var progress: Float = 0F,
    var isAvailable: Boolean = false,
) : Parcelable {
    companion object {
        val getListDummy: List<Chapter>
            get() {
                val data = mutableListOf<Chapter>()
                for (i in 0..10) {
                    data.add(
                        Chapter(
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
