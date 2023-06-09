package com.example.bahasaku.ui.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.model.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    val roomRepository: RoomRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DictionaryState())
    val state: StateFlow<DictionaryState> = _state

    fun onSearchedTextTextFieldValueChanged(query: String) {
        _state.update { it.copy(query = query) }
        onSearch(query)
    }

    private fun onSearch(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(listWord = roomRepository.getAllWord("%$query%")) }
        }
    }
}