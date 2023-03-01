package com.example.bahasaku.data

import android.os.Parcelable
import com.example.bahasaku.core.components.CourseType
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseData(
    val name: String,
    val type: CourseType,
    var isAvailable: Boolean = false,
    var isDone: Boolean = false
) : Parcelable