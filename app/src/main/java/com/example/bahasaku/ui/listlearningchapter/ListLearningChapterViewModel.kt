package com.example.bahasaku.ui.listlearningchapter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.RoomRepository
import com.example.bahasaku.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListLearningChapterViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ListLearningChapterState())
    val state: StateFlow<ListLearningChapterState> = _state

    fun getAllChapter() {
        viewModelScope.launch {
            _state.update { it.copy(listChapter = roomRepository.getAllChapter()) }
        }
    }

    fun getProgress() {
        Log.d("Reditya", "getProgress Launched")
        viewModelScope.launch {
            firestoreRepository.getProgressLearningChapter().collect { response ->
                response?.let { result ->
                    _state.update { it.copy(progress = result) }
                }
            }
        }
    }
}