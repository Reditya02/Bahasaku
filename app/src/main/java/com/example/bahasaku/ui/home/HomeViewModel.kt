package com.example.bahasaku.ui.home

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.Repository
import com.example.bahasaku.data.model.Chapter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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

    fun getAllChapter() {
        viewModelScope.launch {
            _state.update { it.copy(listChapter = repository.getAllChapter()) }
        }
    }

    fun getImage(list: List<Chapter>) {
        viewModelScope.launch {
            val storage = Firebase.storage("gs://bahasaku-6785c.appspot.com").reference
            list.forEachIndexed { index, chapter ->
                storage.child(chapter.iconUrl).downloadUrl.addOnSuccessListener { uri ->
                    _state.update { it.copy(listImage = it.listImage.mapIndexed { i, uri -> if (i == index) uri }) }
                }
            }
        }
    }

}