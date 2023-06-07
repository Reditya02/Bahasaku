package com.example.bahasaku.ui.exercise.exercisepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    fun updateProgress(id: Int, chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.updateExerciseCardResult(id, chapterId)
        }
    }

}