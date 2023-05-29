package com.example.bahasaku.ui.listexercisecard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.FirestoreRepository
import com.example.bahasaku.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListExerciseCardViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ListExerciseCardState())
    val state: StateFlow<ListExerciseCardState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
            _state.update { _state.value.copy(listWord = roomRepository.getAllWordById(chapterId)) }
        }
    }

    fun getProgress(chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.getProgressExerciseCard(chapterId).collect { response ->
                response.let { result ->
                    _state.update { it.copy(progress = result) }
                }
            }
        }
    }

    fun updateChapterProgress(chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.updateExerciseChapterProgress(chapterId)
        }
    }

    fun updateChapterAvailable(chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.updateChapterAvailable(chapterId)
        }
    }
}