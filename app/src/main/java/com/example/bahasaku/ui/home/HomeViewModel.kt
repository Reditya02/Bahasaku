package com.example.bahasaku.ui.home

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.Repository
import com.example.bahasaku.data.model.Chapter
import com.example.bahasaku.data.model.remote.ProgressChapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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

    fun updateProgress() {
        val firebase = FirebaseFirestore.getInstance()
        Firebase.auth.currentUser?.uid?.let { id ->
            firebase
                .collection("progress")
                .document(id)
                .collection("learning_chapter")
                .document("chapter_progress")
                .get()
                .addOnSuccessListener { res ->
                    res?.let {
                        _state.update { it.copy(progress = res.toObject(ProgressChapter::class.java)!!) }
                    }
                }
        }
    }

}