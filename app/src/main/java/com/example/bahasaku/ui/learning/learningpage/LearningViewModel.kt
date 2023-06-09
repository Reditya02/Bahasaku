package com.example.bahasaku.ui.learning.learningpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.model.repository.FirestoreRepository
import com.example.bahasaku.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearningViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LearningState())
    val state: StateFlow<LearningState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
            val res = roomRepository.getAllWordById(chapterId)
            _state.update { _state.value.copy(listWord = res) }
        }
    }

    fun getAllChild(chapterId: String) {
        viewModelScope.launch {
            val res = roomRepository.getAllWordById(chapterId)
            _state.update { _state.value.copy(listChild = res) }
        }
    }

    fun udateCardProgress(chapterId: String, page: Int) {
        viewModelScope.launch {
            firestoreRepository.updateLearningCardProgress(chapterId, page)
        }
    }
}