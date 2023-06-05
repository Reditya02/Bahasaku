package com.example.bahasaku.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
        checkEmpty()
    }

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
        checkEmpty()
    }

    fun onPasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(password = value) }
        checkEmpty()
    }

    fun onRetypePasswordTextFieldValueChanged(value: String) {
        _state.update { it.copy(retypePassword = value) }
        checkEmpty()
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            isRetypePasswordValid()
            _authCondition.emit(AuthCondition.Loading)

            val isValid = _state.value.isRetypePasswordValid
            if (!isValid) {
                _authCondition.emit(AuthCondition.RetypePasswordNotSame)
                return@launch
            }

            register()
        }
    }

    private fun checkEmpty() {
        val isNotEmpty = _state.value.run {
            email.isNotEmpty() && password.isNotEmpty() && retypePassword.isNotEmpty() && name.isNotEmpty()
        }
        _state.update { it.copy(isNotEmpty = isNotEmpty) }
    }

    fun register() = viewModelScope.launch {
        state.value.apply {
            auth.register(email, password, name).collect { response ->
                _authCondition.emit(response)
            }
        }
    }

    private fun isRetypePasswordValid() {
        _state.update { it.copy(isRetypePasswordValid = it.password == it.retypePassword) }
    }

    fun onHideShowPasswordToggled() {
        _state.update { it.copy(isPasswordShown = !_state.value.isPasswordShown) }
    }

    fun onHideShowRetypePasswordToggled() {
        _state.update { it.copy(isRetypePasswordShown = !_state.value.isRetypePasswordShown) }
    }

}