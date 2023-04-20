package com.example.bahasaku.ui.home

import android.net.Uri
import com.example.bahasaku.data.model.Chapter

data class HomeState(
    val listChapter: List<Chapter> = emptyList(),
    val listImage: List<Unit> = List(5) {
        Uri.parse("android.resource://com.example.bahasaku/drawable/placeholder_image")
    }
)
