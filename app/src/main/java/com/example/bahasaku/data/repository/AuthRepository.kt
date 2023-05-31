package com.example.bahasaku.data.repository

import com.example.bahasaku.ui.editprofile.UpdateResult
import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await

class AuthRepository {
    val user = Firebase.auth.currentUser
    val uid = user?.uid ?: ""


    fun login(email: String, password: String) = callbackFlow {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            trySend(AuthCondition.Success)
        } catch (e: Exception) {
            trySend(AuthCondition.Failed)
        }
        awaitClose { channel.close() }
    }

    fun updateName(name: String) = callbackFlow {
        val updateRequest = userProfileChangeRequest {
            displayName = name
        }

        user?.updateProfile(updateRequest)?.addOnSuccessListener {
            trySend(UpdateResult.SUCCESS)
            FirebaseFirestore.getInstance()
                .collection("progress")
                .document(uid)
                .update("name", name)
        }?.addOnFailureListener {
            trySend(UpdateResult.FAILED)
        }

        awaitClose { channel.close() }
    }
}