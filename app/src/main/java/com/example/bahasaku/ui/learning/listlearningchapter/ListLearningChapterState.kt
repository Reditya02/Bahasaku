package com.example.bahasaku.ui.learning.listlearningchapter

import android.net.Uri
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.remote.ProgressChapter

data class ListLearningChapterState(
    val listChapter: List<Chapter> = emptyList(),
    val listImage: List<Unit> = List(5) {
        Uri.parse("android.resource://com.example.bahasaku/drawable/placeholder_image.png")
    },
    val progress: ProgressChapter = ProgressChapter(
        available = mutableListOf(),
        progress = mutableListOf()
    )
)
