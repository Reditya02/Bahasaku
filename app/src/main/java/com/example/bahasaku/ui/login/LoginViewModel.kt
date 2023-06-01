package com.example.bahasaku.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.AuthRepository
import com.example.bahasaku.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    roomRepository: RoomRepository,
    private val auth: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    init {
        viewModelScope.launch {
            roomRepository.getAllChapter()
        }
    }

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onLoginClicked() {
//        isEmailValid()
//        isPasswordValid()

//        val isValid = _state.value.run {
//            isEmailValid && isPasswordValid
//        }

        val isValid = true

        _state.update { it.copy(isLoginValid = isValid) }

        if (isValid) {
            login()
        }
    }

    fun login() = viewModelScope.launch {
        _state.value.apply {
            auth.login(email, password).collect { result ->
                Log.d("Reditya", "Login result $result")
                _state.update { it.copy(authCondition = result) }
            }
//            try {
//                Firebase.auth.signInWithEmailAndPassword(email, password).await()
//                _state.update { it.copy(authCondition = AuthCondition.Success) }
//            } catch (e: Exception) {
//                _state.update { it.copy(authCondition = AuthCondition.Failed) }
//
//            }
        }
    }


//    private fun isEmailValid() {
//        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
//        _state.update { it.copy(isEmailValid = EMAIL_REGEX.toRegex().matches(_state.value.email)) }
//    }
//
//    private fun isPasswordValid() {
//        val isValid = _state.value.password.let {
//            if (it.length < 8)
//                false
//            else it.all { c -> c.isLetterOrDigit() }
//        }
//        _state.update { it.copy(isPasswordValid = isValid) }
//    }

    fun onHideShowPasswordToggled() {
        _state.update { it.copy(isPasswordShown = !_state.value.isPasswordShown) }
    }
}