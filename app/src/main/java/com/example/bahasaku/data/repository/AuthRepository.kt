package com.example.bahasaku.data.repository

import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await

class AuthRepository {
    val uid = Firebase.auth.uid ?: ""

    fun login(email: String, password: String) = callbackFlow {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            trySend(AuthCondition.Success)
        } catch (e: Exception) {
            trySend(AuthCondition.Failed)
        }
        awaitClose { channel.close() }
    }
}