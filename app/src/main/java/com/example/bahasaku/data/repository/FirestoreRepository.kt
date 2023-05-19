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

        val i = chapterId.toInt()
        Log.d("Reditya", "chapterId $i")

        getProgressCard(chapterId).first {
            it?.done.let {
                result = it?.count { it }!!
            }
            true
        }

        var progress = mutableListOf<Int>()

        var available = mutableListOf<Boolean>()

        Log.d("Reditya", "result $result")

        Log.d("Reditya", "init progress $progress")

        Log.d("Reditya", "init available $available")

        getProgressChapter().first {
            it?.let {
                progress = it.progress
                available = it.available

                Log.d("Reditya", "get progress $progress")

                Log.d("Reditya", "get available $available")
            }
            true
        }

        progress[i] = result
        if (result == progress.size - 1 && i < 8) {
            Log.d("Reditya", "update available")
            available[i+1] = true
        }

        fsChapterProgress
            .update("progress", progress)

    }

    suspend fun updateChapterAvailable(chapterId: String) {
        val i = chapterId.toInt()
        Log.d("Reditya", "chapterId $i")

        var available = mutableListOf<Boolean>()

        Log.d("Reditya", "init available $available")

        getProgressChapter().first {
            it?.let {
                available = it.available

                Log.d("Reditya", "get available $available")
            }
            true
        }

        if ( i < 8) {
            Log.d("Reditya", "update available")
            available[i+1] = true
        }

        fsChapterProgress
            .update("available", available)

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
