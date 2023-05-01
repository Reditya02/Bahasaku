package com.example.bahasaku.data.model.remote

import com.google.firebase.firestore.PropertyName

data class ProgressCard(
    val available: MutableList<Boolean> = mutableListOf(),
    val done: MutableList<Boolean> = mutableListOf()
)
