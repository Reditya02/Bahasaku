package com.example.bahasaku.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {
    val uid = Firebase.auth.uid ?: ""
}