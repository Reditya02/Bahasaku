package com.example.bahasaku.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.model.User
import com.example.bahasaku.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(User())
    val state: StateFlow<User> = _state

    fun getUser() {
        viewModelScope.launch {
            firestoreRepository.getUserProgress().collect { response ->
                Log.d("Reditya", "getUser response $response")
                response?.let {
                    _state.update { response }
                }
            }
        }
    }
}