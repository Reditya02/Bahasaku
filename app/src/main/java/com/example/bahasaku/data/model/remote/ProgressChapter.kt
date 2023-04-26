package com.example.bahasaku.data.model.remote

import com.google.firebase.firestore.PropertyName

data class ProgressChapter(
    @get:PropertyName("isAvailable")
    val isAvailable: MutableList<Boolean> = mutableListOf(),
    val progress: MutableList<Int> = mutableListOf()
)
