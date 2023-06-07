package com.example.bahasaku.ui.exercise.listexercisechapter

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
class ListExerciseChapterViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ListExerciseChapterState())
    val state: StateFlow<ListExerciseChapterState> = _state

    fun getAllChapter() {
        viewModelScope.launch {
            _state.update { it.copy(listChapter = roomRepository.getAllChapter()) }
        }
    }

    fun getProgress() {
        viewModelScope.launch {
            firestoreRepository.getProgressExerciseChapter().collect { response ->
                response.let { result ->
                    _state.update { it.copy(progress = result) }
                }
            }
        }
    }

    fun updateScore() {
        viewModelScope.launch {
            firestoreRepository.countScore()
        }
    }
}