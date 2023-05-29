package com.example.bahasaku.ui.exercise

import android.util.Log
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
class ExerciseViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ExerciseState())
    val state: StateFlow<ExerciseState> = _state

    fun updateProgress(id: Int, chapterId: String) {
        viewModelScope.launch {
            Log.d("Reditya", "ViewModel id $id chapterId $chapterId")
            firestoreRepository.updateExerciseCardResult(id, chapterId)
        }
    }

}