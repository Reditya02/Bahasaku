package com.example.bahasaku.data.repository

import android.net.Uri
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.example.bahasaku.ui.editprofile.UpdateResult
import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepository {
    fun user() = Firebase.auth.currentUser

    fun getUid(): String {
        return Firebase.auth.currentUser?.uid ?: ""
    }

    fun login(email: String, password: String) = callbackFlow {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            trySend(AuthCondition.Success)
        } catch (e: FirebaseAuthInvalidUserException) {
            trySend(AuthCondition.NotRegistered)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            trySend(AuthCondition.WrongFormat)
        } catch (e: Exception) {
            trySend(AuthCondition.Failed)
        }
        awaitClose { channel.close() }
    }

    fun register(email: String, password: String, name: String) = callbackFlow {
        try {
            Firebase.auth.apply {
                createUserWithEmailAndPassword(email, password).await()
                currentUser?.apply {
                    val photo = ""
                    createProgress(uid, name, photo)
                    updateProfile(userProfileChangeRequest {
                        displayName = name
                        photoUri = Uri.parse(photo)
                    })
                }
                trySend(AuthCondition.Success)
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            trySend(AuthCondition.NotRegistered)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            trySend(AuthCondition.WrongFormat)
        } catch (e: Exception) {
            trySend(AuthCondition.Failed)
        }
        awaitClose { channel.close() }
    }

    private fun createProgress(id: String, name: String, imageUrl: String) {
        FirebaseFirestore
            .getInstance()
            .collection("progress")
            .document(id)
            .apply {

                set(hashMapOf(
                    "score" to 0,
                    "name" to name,
                    "image" to imageUrl
                ))

                collection("learning_chapter")
                    .document("chapter_progress")
                    .set(
                        ProgressChapter(
                        available = mutableListOf(
                            true, false, false, false, false, false, false, false
                        ),
                        progress = mutableListOf(
                            0, 0, 0, 0, 0, 0, 0, 0
                        ),
                    )
                    )

                collection("exercise_chapter")
                    .document("chapter_progress")
                    .set(
                        ProgressChapter(
                        progress = mutableListOf(
                            0, 0, 0, 0, 0, 0, 0, 0
                        ),
                    )
                    )

                collection("learning_card")
                    .apply {
                        document("00")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false
                                )
                            )
                            )

                        document("01")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false
                                )
                            )
                            )

                        document("02")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                )
                            )
                            )

                        document("03")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                )
                            )
                            )

                        document("04")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                )
                            )
                            )

                        document("05")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            )
                            )

                        document("06")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false
                                )
                            )
                            )

                        document("07")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            )
                            )
                    }

                collection("exercise_card")
                    .apply {
                        document("00")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false
                                )
                            )
                            )

                        document("01")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false
                                )
                            )
                            )

                        document("02")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                )
                            )
                            )

                        document("03")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                )
                            )
                            )

                        document("04")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                )
                            )
                            )

                        document("05")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            )
                            )

                        document("06")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false
                                )
                            )
                            )

                        document("07")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            )
                            )

                        document("12")
                            .set(
                                ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                )
                            )
                            )
                    }
            }
    }

    fun updateName(name: String) = callbackFlow {
        val updateRequest = userProfileChangeRequest {
            displayName = name
        }

        user()?.updateProfile(updateRequest)?.addOnSuccessListener {
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