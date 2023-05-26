package com.example.bahasaku.ui.detailcard

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
class DetailCardViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailCardState())
    val state: StateFlow<DetailCardState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
            val res = roomRepository.getAllWordById(chapterId)
            _state.update { _state.value.copy(listWord = res) }
        }
    }

    fun udateCardProgress(chapterId: String, page: Int) {
        viewModelScope.launch {
            firestoreRepository.updateLearningCardProgress(chapterId, page)
        }
    }

    fun updateChapterAvailable(chapterId: String) {
        viewModelScope.launch {
            firestoreRepository.updateChapterAvailable(chapterId)
        }
    }

    fun getChild(id: String) {

    }
}