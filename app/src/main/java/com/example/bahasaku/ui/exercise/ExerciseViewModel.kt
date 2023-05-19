package com.example.bahasaku.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ExerciseState())
    val state: StateFlow<ExerciseState> = _state

    fun onEssayAnswerTextFieldValueChanged(value: String) {
        _state.update { it.copy(answer = value) }
    }

    fun getAllQuestion(courseId: String) {
        viewModelScope.launch {
            _state.update { it.copy(question = roomRepository.getAllQuestion(courseId)) }
        }
    }

    fun nextQuestion() {
        _state.apply {
            if (value.position != value.question.size - 1) {
                val pos = value.position
                update { it.copy(position = pos  + 1) }
            }
        }
    }

    fun updateAnswer(answer: String) {
        _state.update { it.copy(answer = answer) }
    }

    fun checkAnswer(): ExerciseCondition {
        var res: ExerciseCondition
        _state.apply {
            value.apply {
                res = if (answer == question[position].answer)
                    ExerciseCondition.Correct
                else
                    ExerciseCondition.Wrong
            }
            update {
                it.copy(exerciseCondition = it.exerciseCondition.mapIndexed { i, e ->
                    if (i == it.position)
                        res
                    else
                        e
                })
            }
        }

        return res
    }
}