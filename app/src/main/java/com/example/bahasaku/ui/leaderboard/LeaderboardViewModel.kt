package com.example.bahasaku.ui.leaderboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bahasaku.data.repository.AuthRepository
import com.example.bahasaku.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LeaderboardState())
    val state: StateFlow<LeaderboardState> = _state

    fun getLeaderboard() {
        viewModelScope.launch {
            firestoreRepository.getLeaderboard().collect { response ->
                _state.update { _state.value.copy(listUser = response) }
                Log.d("Reditya", "Leaderboard \n $response")
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            firestoreRepository.getUserProgress().collect { response ->
                Log.d("Reditya", "Current user $response")
                response?.let {
                    _state.update { _state.value.copy(currentUser = response) }
                }
            }
        }

    }
}