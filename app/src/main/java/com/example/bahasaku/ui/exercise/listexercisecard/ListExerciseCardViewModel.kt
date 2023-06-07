package com.example.bahasaku.ui.exercise.listexercisecard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.model.remote.ProgressCard
import com.example.bahasaku.data.repository.FirestoreRepository
import com.example.bahasaku.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    fun getAllCardWithChild(chapterId: String, childId: String) {
        viewModelScope.launch {
            val parentWord = roomRepository.getAllWordById(chapterId)
            val childWord = roomRepository.getAllWordById(childId)

            _state.update { _state.value.copy(listWord = combineArray(parentWord, childWord)) }
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

    fun getProgressWithChild(chapterId: String, childId: String) {
        viewModelScope.launch {
            val parent = firestoreRepository.getProgressExerciseCard(chapterId).first()
            val child = firestoreRepository.getProgressExerciseCard(childId, chapterId).first()

            val done = combineArray(parent.done, child.done) as MutableList<Boolean>
            val available = combineArray(parent.available, child.available) as MutableList<Boolean>

            _state.update { _state.value.copy(progress = ProgressCard(done = done, available = available)) }
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

    private fun <T> combineArray(a1: List<T>, a2: List<T>): List<T> {
        val result = mutableListOf<T>()
        a1.forEachIndexed { i, _ ->
            result.add(a1[i])
            result.add(a2[i])
        }

        return result
    }
}