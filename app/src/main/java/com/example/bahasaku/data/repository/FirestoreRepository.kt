package com.example.bahasaku.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.bahasaku.data.model.ListUser
import com.example.bahasaku.data.model.User
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.firestore.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    auth: AuthRepository
) {
    private val uid = auth.uid

    private val fsProgress = FirebaseFirestore
        .getInstance()
        .collection("progress")
        .document(uid)

    private val fsLearningChapterProgress = fsProgress
        .collection("learning_chapter")
        .document("chapter_progress")

    private val fsExerciseChapterProgress = fsProgress
        .collection("exercise_chapter")
        .document("chapter_progress")

    fun getProgressLearningChapter() = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }
                if (value != null && value.exists()) {
                    val result = value.toObject(ProgressChapter::class.java)
                    trySend(result)
                }
            }
        }

        val firebase = fsLearningChapterProgress
            .addSnapshotListener(listener)
        awaitClose { firebase.remove() }
    }

    fun getProgressLearningCard(chapterId: String) = callbackFlow {
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

    private fun getProgressFieldExerciseChapter() = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }
                if (value != null && value.exists()) {
                    val result = value.get("progress")
                    trySend(result)
                }
            }
        }
        val firebase = fsExerciseChapterProgress
            .addSnapshotListener(listener)
        awaitClose { firebase.remove() }
    }

    fun getProgressExerciseChapter() = callbackFlow {
        val result = MutableStateFlow(ProgressChapter())

        getProgressLearningChapter().first { progress ->
            if (progress != null) {
                result.update { it.copy(available = progress.available) }
            }
            true
        }

        getProgressFieldExerciseChapter().first { progress ->
            if (progress != null) {
                result.update { it.copy(progress = progress as MutableList<Int>) }
            }
            true
        }

        trySend(result.value)
        awaitClose { channel.close() }
    }

    fun getDoneFieldExerciseCard(chapterId: String) = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }
                if (value != null && value.exists()) {
                    val result = value["done"]
                    trySend(result)
                }
            }
        }

        val firebase = fsProgress
            .collection("exercise_card")
            .document(chapterId)
            .addSnapshotListener(listener)
        awaitClose { firebase.remove() }
    }

    fun getProgressExerciseCard(chapterId: String, parent: String = "") = callbackFlow {
        val result = MutableStateFlow(ProgressCard())

        if (parent.isEmpty()) {
            getProgressLearningCard(chapterId).first { res ->
                if (res != null) {
                    result.update { it.copy(available = res.done) }
                }
                true
            }
        } else {
            getProgressLearningCard(parent).first { res ->
                if (res != null) {
                    result.update { it.copy(available = res.done) }
                }
                true
            }
        }

        getDoneFieldExerciseCard(chapterId).first { res ->
            if (res != null) {
                result.update {it.copy(done = res as MutableList<Boolean>)}
            }
            true
        }

        trySend(result.value)
        awaitClose { channel.close() }
    }

    fun getLeaderboard() = callbackFlow {
        val listener = object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    cancel()
                    return
                }
                if (value != null) {
                    val list = mutableListOf<String>()
                    val res = value.toObjects(User::class.java)
//                    value.documents.forEach {
//                        val res = it.get("name")
//                        list.add((res ?: "null") as String)
//                    }
                    trySend(res)
                }
            }
        }

        val firebase = FirebaseFirestore.getInstance()
            .collection("progress")
            .orderBy("score", Query.Direction.DESCENDING)
            .addSnapshotListener(listener)

        awaitClose { firebase.remove() }
    }

    suspend fun updateChapterAvailable(chapterId: String) {
        val i = chapterId.toInt()
        var available = mutableListOf<Boolean>()

        getProgressLearningChapter().first {
            it?.let { available = it.available }
            true
        }

        if ( i < 8) { available[i+1] = true }
        fsLearningChapterProgress.update("available", available)
    }

    suspend fun updateLearningChapterProgress(chapterId: String) {
        var result = 0
        val i = chapterId.toInt()

        getProgressLearningCard(chapterId).first {
            it?.done.let { result = it?.count { it }!! }
            true
        }

        var progress = mutableListOf<Int>()
        var available = mutableListOf<Boolean>()

        getProgressLearningChapter().first {
            it?.let {
                progress = it.progress
                available = it.available
            }
            true
        }

        progress[i] = result
        if (result == progress.size - 1 && i < 8) { available[i+1] = true }
        fsLearningChapterProgress.update("progress", progress)
    }

    suspend fun updateLearningCardProgress(chapterId: String, page: Int) {
        var result = ProgressCard()
        getProgressLearningCard(chapterId).first {
            it?.let { result = it }
            true
        }

        if (!result.done[page]) { result.done[page] = true }
        if (page != result.done.size - 1) { result.available[page + 1] = true }

        fsProgress
            .collection("learning_card")
            .document(chapterId)
            .set(result)
    }

    suspend fun updateExerciseChapterProgress(chapterId: String) {
        var result = 0
        val i = chapterId.toInt()

        getProgressExerciseCard(chapterId).first { res ->
            res.done.let { result = it.count { it } }
            true
        }

        var progress = mutableListOf<Int>()
        var available = mutableListOf<Boolean>()

        getProgressExerciseChapter().first {
            it.let {
                progress = it.progress
                available = it.available
            }
            true
        }

        progress[i] = result

        if (result == progress.size - 1 && i < 8) { available[i+1] = true }
        fsExerciseChapterProgress.update("progress", progress)
    }

    suspend fun updateExerciseCardResult(id: Int, chapterId: String) {
        var result = mutableListOf<Boolean>()

        getProgressExerciseCard(chapterId).first {
            result = it.done
            true
        }
        result[id] = true
        fsProgress
            .collection("exercise_card")
            .document(chapterId)
            .update("done", result)
    }

    suspend fun countScore() {
        var result = 0

        getProgressExerciseChapter().first {
            it.progress.forEach { score ->
                result += score
            }
            true
        }

        Log.d("Reditya", "Score $result")

        val firebase = fsProgress.update("score", result)

    }
}
