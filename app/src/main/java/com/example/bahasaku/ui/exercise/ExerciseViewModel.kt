package com.example.bahasaku.ui.exercise

import androidx.lifecycle.ViewModel
import com.example.bahasaku.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(ExerciseState())
    val state: StateFlow<ExerciseState> = _state

    fun onEssayAnswerTextFieldValueChanged(value: String) {
        _state.update { it.copy(essayAnswer = value) }
    }
}