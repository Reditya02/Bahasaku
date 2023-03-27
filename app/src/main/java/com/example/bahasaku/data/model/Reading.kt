package com.example.bahasaku.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Reading(
    val id: String = "",
    val courseId: String = "",
    var material: List<String> = listOf()
) : Parcelable
