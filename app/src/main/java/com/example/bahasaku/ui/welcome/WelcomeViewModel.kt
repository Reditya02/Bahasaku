package com.example.bahasaku.ui.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bahasaku.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    authRepository: AuthRepository
) : ViewModel() {
    init {
        val id = authRepository.getUid()
    }
}