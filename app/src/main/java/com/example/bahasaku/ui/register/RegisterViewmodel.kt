package com.example.bahasaku.ui.register

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.RoomRepository
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.example.bahasaku.data.repository.AuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewmodel @Inject constructor(
    private val auth: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    private val _authCondition = MutableSharedFlow<AuthCondition>()
    val authCondition: SharedFlow<AuthCondition> = _authCondition

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
        viewModelScope.launch {
            isRetypePasswordValid()
            _authCondition.emit(AuthCondition.Loading)

            var isValid = _state.value.run {
                email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()
            }
            if (!isValid) {
                _authCondition.emit(AuthCondition.Empty)
                return@launch
            }

            isValid = isValid && _state.value.isRetypePasswordValid
            if (!isValid) {
                _authCondition.emit(AuthCondition.RetypePasswordNotSame)
                return@launch
            }

            register()
        }
    }

    fun register() = viewModelScope.launch {
        state.value.apply {
            auth.register(email, password, name).collect { response ->
                _authCondition.emit(response)
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