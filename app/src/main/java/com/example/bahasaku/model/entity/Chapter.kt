package com.example.bahasaku.model.entity

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
    val cardNumber: Int = 0,
    val exerciseNumber: Int = 0,
    val imageUrl: String = "",
    val chapterChild: String = "",
    val isChild: Boolean = false
) : Parcelable
