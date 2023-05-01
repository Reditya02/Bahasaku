package com.example.bahasaku.data.model.remote

import com.google.firebase.firestore.PropertyName

data class ProgressChapter(
    val available: MutableList<Boolean> = mutableListOf(),
    val progress: MutableList<Int> = mutableListOf()
)