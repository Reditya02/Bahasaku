package com.example.bahasaku.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.AuthRepository
import com.example.bahasaku.data.repository.RoomRepository
import com.example.bahasaku.ui.register.AuthCondition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    roomRepository: RoomRepository,
    private val auth: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    private val _authCondition = MutableSharedFlow<AuthCondition>()
    val authCondition: SharedFlow<AuthCondition> = _authCondition

    init {
        viewModelScope.launch {
            roomRepository.getAllChapter()
        }
    }

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
        checkEmpty()
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
        checkEmpty()
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            _authCondition.emit(AuthCondition.Loading)

            login()

//            _state.update { it.copy(isLoginValid = isValid) }
//
//            if (isValid) {
//            } else {
//                _authCondition.emit(AuthCondition.Empty)
//            }
        }
    }

    private fun checkEmpty() {
        val isNotEmpty = _state.value.run {
            email.isNotEmpty() && password.isNotEmpty()
        }
        _state.update { it.copy(isNotEmpty = isNotEmpty) }
    }

    fun login() = viewModelScope.launch {
        _state.value.apply {
            auth.login(email, password).collect { result ->
                _authCondition.emit(result)
            }
        }
    }

    fun onHideShowPasswordToggled() {
        _state.update { it.copy(isPasswordShown = !_state.value.isPasswordShown) }
    }
}