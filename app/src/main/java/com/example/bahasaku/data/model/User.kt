package com.example.bahasaku.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String = "",
    val image: String = "",
    val score: Int = 0
) : Parcelable