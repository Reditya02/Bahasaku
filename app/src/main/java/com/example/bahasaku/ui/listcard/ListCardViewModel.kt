package com.example.bahasaku.ui.listcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.RoomRepository
import com.example.bahasaku.data.model.remote.ProgressCard
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
class ListCardViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ListCardState())
    val state: StateFlow<ListCardState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
            _state.update { _state.value.copy(listWord = roomRepository.getAllWordById(chapterId)) }
        }
    }

    fun getProgress(chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.getProgressCard(chapterId).collect { response ->
                response?.let { result ->
                    _state.update { it.copy(progress = result) }
                }
            }
        }
    }

    fun updateChapterProgress(chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.updateChapterProgress(chapterId)
        }
    }
}