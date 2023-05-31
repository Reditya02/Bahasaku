package com.example.bahasaku.data.repository

import android.util.Log
import com.example.bahasaku.ui.editprofile.UpdateResult
import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepository {
    var user = Firebase.auth.currentUser

    fun getUid(): String {
        return Firebase.auth.currentUser?.uid ?: ""
    }

    fun login(email: String, password: String) = callbackFlow {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            trySend(AuthCondition.Success)
            Log.d("Reditya", "saved uid ${getUid()} \n new uid ${Firebase.auth.currentUser?.uid ?: "0"}")
//            user = Firebase.auth.currentUser
//            uid = user?.uid ?: ""
        } catch (e: Exception) {
            Log.d("Reditya", "Login exception")
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
                .document(getUid())
                .update("name", name)
        }?.addOnFailureListener {
            trySend(UpdateResult.FAILED)
        }

        awaitClose { channel.close() }
    }
}