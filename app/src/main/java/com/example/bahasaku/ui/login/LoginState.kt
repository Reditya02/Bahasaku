package com.example.bahasaku.ui.login

data class LoginState(
    val email: String = "",
    val password: String = "",

    val isLoginValid: Boolean = false,

    val isNotEmpty: Boolean = false,

    val isPasswordShown: Boolean = false
)
