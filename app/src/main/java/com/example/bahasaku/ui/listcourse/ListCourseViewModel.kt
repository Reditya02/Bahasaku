package com.example.bahasaku.ui.listcourse

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCourseViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(ListCourseState())
    var state: StateFlow<ListCourseState> = _state

    fun getAllCourse(chapterId: String) {
        viewModelScope.launch {
            _state.update { _state.value.copy(listCourse = repository.getAllCourse(chapterId)) }
        }
    }
}