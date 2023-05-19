package com.example.bahasaku.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.RoomRepository
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.example.bahasaku.data.repository.FirestoreRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun getAllChapter() {
        viewModelScope.launch {
            _state.update { it.copy(listChapter = roomRepository.getAllChapter()) }
        }
    }

    fun updateProgress() {
        viewModelScope.launch {
            firestoreRepository.getProgress().collect { response ->
                response?.let { result ->
                    _state.update { it.copy(progress = result) }
                }
            }
        }

        Log.d("Reditya", "viewmodel ${state.value.progress}")
    }
}