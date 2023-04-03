package com.example.bahasaku.ui.login

import com.example.bahasaku.ui.register.AuthCondition

data class LoginState(
    val email: String = "",
    val password: String = "",

    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLoginValid: Boolean = false,

    val authCondition: AuthCondition = AuthCondition.None
)
