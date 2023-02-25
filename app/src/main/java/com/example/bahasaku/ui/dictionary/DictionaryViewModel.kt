package com.example.bahasaku.ui.dictionary

import androidx.lifecycle.ViewModel
import com.example.bahasaku.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(DictionaryState())
    val state: StateFlow<DictionaryState> = _state

    fun onSearchedTextTextFieldValueChanged(value: String) {
        _state.update { it.copy(searchedText = value) }
    }
}