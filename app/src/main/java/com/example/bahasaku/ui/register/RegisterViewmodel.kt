package com.example.bahasaku.ui.register

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.RoomRepository
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewmodel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onNameTextFieldValueChanged(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onRetypePasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(retypePassword = value) }
    }

    fun onRegisterClicked() {
        isEmailValid()
        isPasswordValid()
        isRetypePasswordValid()
        isNameValid()

        val isValid: Boolean= _state.value.run {
            isEmailValid && isPasswordValid && isRetypePasswordValid && isNameValid
        }

        _state.update { it.copy(isRegisterValid = isValid) }

        if (isValid) {
            register()
        }
    }

    fun register() = viewModelScope.launch {
        _state.value.apply {
            try {
                val fbAuth = Firebase.auth
                fbAuth.createUserWithEmailAndPassword(email, password).await()
                fbAuth.currentUser?.apply {
                    createProgress(uid, name, uid)
                    updateProfile(userProfileChangeRequest {
                        displayName = name
                        photoUri = Uri.parse("")
                    })
                }
                _state.update { it.copy(authCondition = AuthCondition.Success) }
            } catch (e: Exception) {
                Log.d("Reditya", e.toString())
                _state.update { it.copy(authCondition = AuthCondition.Failed) }
            }
        }
    }

    fun isEmailValid() {
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        _state.update { it.copy(isEmailValid = EMAIL_REGEX.toRegex().matches(_state.value.email)) }
    }

    fun isPasswordValid() {
        val isValid = _state.value.password.let {
            if (it.length < 8)
                false
            else it.all { c -> c.isLetterOrDigit() }
        }
        _state.update { it.copy(isPasswordValid = isValid) }
    }

    fun isRetypePasswordValid() {
        _state.update { it.copy(isRetypePasswordValid = it.password == it.retypePassword) }
    }

    fun isNameValid() {
        _state.update { it.copy(isNameValid = it.name.all { c -> c.isLetter() }) }
    }

    fun onHideShowPasswordToggled() {
        _state.update { it.copy(isPasswordShown = !_state.value.isPasswordShown) }
    }

    fun onHideShowRetypePasswordToggled() {
        _state.update { it.copy(isRetypePasswordShown = !_state.value.isRetypePasswordShown) }
    }

    fun createProgress(id: String, name: String, imageUrl: String) {
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
                    .set(ProgressChapter(
                        available = mutableListOf(
                            true, false, false, false, false, false, false, false
                        ),
                        progress = mutableListOf(
                            0, 0, 0, 0, 0, 0, 0, 0
                        ),
                    ))

                collection("exercise_chapter")
                    .document("chapter_progress")
                    .set(ProgressChapter(
                        progress = mutableListOf(
                            0, 0, 0, 0, 0, 0, 0, 0
                        ),
                    ))

                collection("learning_card")
                    .apply {
                        document("00")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false
                                )
                            ))

                        document("01")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false
                                )
                            ))

                        document("02")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                )
                            ))

                        document("03")
                            .set(ProgressCard(
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
                            ))

                        document("04")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                )
                            ))

                        document("05")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            ))

                        document("06")
                            .set(ProgressCard(
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
                            ))

                        document("07")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    true, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            ))
                    }

                collection("exercise_card")
                    .apply {
                        document("00")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false
                                )
                            ))

                        document("01")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false
                                )
                            ))

                        document("02")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                )
                            ))

                        document("03")
                            .set(ProgressCard(
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
                            ))

                        document("04")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false, false, false
                                )
                            ))

                        document("05")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            ))

                        document("06")
                            .set(ProgressCard(
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
                            ))

                        document("07")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false, false, false
                                )
                            ))

                        document("12")
                            .set(ProgressCard(
                                available = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                ),
                                done = mutableListOf(
                                    false, false, false, false, false, false, false, false, false, false,
                                    false, false
                                )
                            ))
                    }
            }
    }
}