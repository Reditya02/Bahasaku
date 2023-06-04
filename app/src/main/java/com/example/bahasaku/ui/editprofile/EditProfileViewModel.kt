package com.example.bahasaku.ui.editprofile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.AuthRepository
import com.example.bahasaku.data.repository.FirestoreRepository
import com.example.bahasaku.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val auth: AuthRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(EditProfileState())
    val state: StateFlow<EditProfileState> = _state

    fun onNameTextFieldValueChanged(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun uploadImage(uri: Uri) {
        viewModelScope.launch {
            val res = firestoreRepository.uploadImage(uri)
            _state.update { it.copy(
                updateResult = if (res) UpdateResult.SUCCESS else UpdateResult.FAILED
            ) }
        }
    }

    fun onUpdateButtonClicked() {
        viewModelScope.launch {
            auth.updateName(state.value.name).collect { result ->
                _state.update { it.copy(updateResult = result) }
            }
        }
    }
}