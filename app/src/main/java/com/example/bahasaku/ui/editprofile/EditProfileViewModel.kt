package com.example.bahasaku.ui.editprofile

import androidx.lifecycle.ViewModel
import com.example.bahasaku.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(EditProfileState())
    val state: StateFlow<EditProfileState> = _state

    fun onEmailTextFieldValueChanged(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onNameTextFieldValueChanged(value: String) {
        _state.update { it.copy(name = value) }
    }
}