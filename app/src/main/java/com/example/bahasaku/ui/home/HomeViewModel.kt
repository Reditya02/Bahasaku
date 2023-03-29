package com.example.bahasaku.ui.home

import android.util.Log
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
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getAllChapter()
    }

    fun getAllChapter() {
        viewModelScope.launch {
            Log.d("Reditya", "inside VM ${repository.getAllChapter()}")
            _state.update { it.copy(listChapter = repository.getAllChapter()) }
        }
    }

}