package com.example.bahasaku.ui.detailcard

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
class DetailCardViewModel @Inject constructor(
    val roomRepository: RoomRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailCardState())
    val state: StateFlow<DetailCardState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
//            Log.d("Reditya", "DetailCardViewModel $chapterId")
            val res = roomRepository.getAllWordById(chapterId)
//            Log.d("Reditya", "DetailCardViewModel Res $res")
            _state.update { _state.value.copy(listWord = res) }
//            Log.d("Reditya", "DetailCardViewModel State ${state.value.listWord}")
        }
    }

    fun getChild(id: String) {

    }
}