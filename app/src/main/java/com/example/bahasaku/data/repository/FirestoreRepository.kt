package com.example.bahasaku.data.repository

import android.util.Log
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    auth: AuthRepository
) {
    private val uid = auth.uid

    private val fsProgress = FirebaseFirestore
        .getInstance()
        .collection("progress")
        .document(uid)

    private val fsChapterProgress = fsProgress
        .collection("learning_chapter")
        .document("chapter_progress")

    fun getProgressChapter() = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }
                if (value != null && value.exists()) {
                    val result = value.toObject(ProgressChapter::class.java)
                    trySend(result)
                } else {
                }
            }
        }

        val firebase = fsChapterProgress
            .addSnapshotListener(listener)
        awaitClose { firebase.remove() }
    }

    suspend fun updateChapterProgress(chapterId: String) {
        var result = 0
        getProgressCard(chapterId).first {
            it?.done.let {
                result = it?.count { it }!!
            }
            true
        }

        var progress = mutableListOf<Int>()

        getProgressChapter().first {
            it?.progress.let {
                if (it != null) {
                    progress = it
                }
            }
            true
        }

        progress[chapterId.toInt()] = result

        fsChapterProgress
            .update("progress", progress)

    }

    fun getProgressCard(chapterId: String) = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }
                if (value != null && value.exists()) {
                    val result = value.toObject(ProgressCard::class.java)
                    trySend(result)
                }
            }
        }

        val firebase = fsProgress
            .collection("learning_card")
            .document(chapterId)
            .addSnapshotListener(listener)
        awaitClose { firebase.remove() }
    }

    suspend fun updateCardProgress(chapterId: String, page: Int) {
        Log.d("Reditya", "repository $chapterId $page")
        var result = ProgressCard()
        getProgressCard(chapterId).first {
            it?.let {
                result = it
            }
            true
        }

        if (!result.done[page]) {
            result.done[page] = true
        }

        if (page != result.done.size - 1) {
            result.available[page + 1] = true
        }

        fsProgress
            .collection("learning_card")
            .document(chapterId)
            .set(result)
    }
}
