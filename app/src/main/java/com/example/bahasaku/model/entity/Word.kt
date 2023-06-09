package com.example.bahasaku.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Word(
    @PrimaryKey
    val id: String = "",
    val chapterId: String = "",
    val indonesian: String = "",
    val balinese: String = "",
    val imageUrl: String = "",
    val option: String = "",
    val wordChild: String = "",
    val audio: Int = 0
) : Parcelable {
    fun getOptionList(): List<String> {
        val option = option.split("|").shuffled().take(3) as MutableList
        option.add(balinese)
        return option.shuffled()
    }
}
