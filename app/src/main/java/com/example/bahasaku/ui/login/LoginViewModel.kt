package com.example.bahasaku.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.Repository
import com.example.bahasaku.ui.register.AuthCondition
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onLoginClicked() {
        isEmailValid()
        isPasswordValid()

        val isValid = _state.value.run {
            isEmailValid && isPasswordValid
        }

        _state.update { it.copy(isLoginValid = isValid) }

        if (isValid) {
            login()
        }
    }

    fun login() = viewModelScope.launch {
        _state.value.apply {
            try {
//                    loadingState.emit(LoadingState.LOADING)
                Log.d("Reditya", "Start Login")
                Firebase.auth.signInWithEmailAndPassword(email, password).await()
//                    loadingState.emit(LoadingState.LOADED)
                _state.update { it.copy(authCondition = AuthCondition.Success) }
                Log.d("Reditya", "Complete Login")
            } catch (e: Exception) {
//                    loadingState.emit(LoadingState.error(e.localizedMessage))
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
}