package com.example.bahasaku.ui.register

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
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
    private val repository: Repository
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
//                    loadingState.emit(LoadingState.LOADING)
                Log.d("Reditya", "Start Register")
                Firebase.auth.createUserWithEmailAndPassword(email, password).await()
//                    loadingState.emit
                Firebase.auth.currentUser?.updateProfile(userProfileChangeRequest {
                    displayName = name
                    photoUri = Uri.parse("")
                })
//                repository.populateDatabase()
                Log.d("Reditya", "Complete Register")
            } catch (e: Exception) {
//                    loadingState.emit(LoadingState.error(e.localizedMessage))
                Log.d("Reditya", e.toString())

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
}