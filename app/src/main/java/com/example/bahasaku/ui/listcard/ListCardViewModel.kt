package com.example.bahasaku.ui.listcard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.Repository
import com.example.bahasaku.data.model.remote.ProgressCard
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(ListCardState())
    val state: StateFlow<ListCardState> = _state

    fun getAllCard(chapterId: String) {
        viewModelScope.launch {
            _state.update { _state.value.copy(listWord = repository.getAllWordById(chapterId)) }
        }
    }

    fun updateProgress(chapterId: String) {
        val firebase = FirebaseFirestore.getInstance()
        Firebase.auth.currentUser?.uid?.let { id ->
            firebase
                .collection("progress")
                .document(id)
                .collection("learning_card")
                .document(chapterId)
                .get()
                .addOnSuccessListener { res ->
                    res?.let {
                        _state.update { it.copy(progress = res.toObject(ProgressCard::class.java)!!) }
                    }
                }
        }
    }
}