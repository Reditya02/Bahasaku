package com.example.bahasaku.ui.detailcard

import android.util.Log
import androidx.compose.runtime.MutableState
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
class DetailCardViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailCardState())
    val state: StateFlow<DetailCardState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
//            Log.d("Reditya", "DetailCardViewModel $chapterId")
            val res = repository.getAllWordById(chapterId)
//            Log.d("Reditya", "DetailCardViewModel Res $res")
            _state.update { _state.value.copy(listWord = res) }
//            Log.d("Reditya", "DetailCardViewModel State ${state.value.listWord}")
        }
    }

    fun getChild(id: String) {

    }
}