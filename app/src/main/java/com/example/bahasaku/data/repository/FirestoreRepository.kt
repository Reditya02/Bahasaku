package com.example.bahasaku.data.repository

import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    auth: AuthRepository
) {
    private val uid = auth.uid

    fun getProgress() = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }

                if (value != null && value.exists()) {
                    // The user document has data
                    val user = value.toObject(ProgressChapter::class.java)
                    trySend(user)
                } else {
                    // The user document does not exist or has no data
                }
            }
        }

        val firebase = FirebaseFirestore.getInstance()
            .collection("progress")
            .document(uid)
            .collection("learning_chapter")
            .document("chapter_progress")
            .addSnapshotListener(listener)

        awaitClose { firebase.remove() }

    }
}

//.addSnapshotListener { res, e ->
//    Log.d("Reditya", "firebase $res")
//    val result = res!!.toObject(ProgressChapter::class.java)
//    val response = res?.let {
//        val result = it.toObject(ProgressChapter::class.java)
//        result
//    } ?: run {
//        Response.Failure(e)
//    }
//    trySend(response)
//}

//try {
//    FirebaseFirestore.getInstance()
//        .collection("progress")
//        .document(uid)
//        .collection("learning_chapter")
//        .document("chapter_progress")
//        .get()
//        .addOnSuccessListener { res ->
//            Log.d("Reditya", "firebase $res")
//            Response.Success(res)
//        }
//
//} catch (e: Exception) {
//    Response.Failure(e)
//}