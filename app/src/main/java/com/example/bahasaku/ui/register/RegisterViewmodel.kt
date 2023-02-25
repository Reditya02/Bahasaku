package com.example.bahasaku.ui.register

import androidx.lifecycle.ViewModel
import com.example.bahasaku.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewmodel @Inject constructor(
    repository: Repository
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
}