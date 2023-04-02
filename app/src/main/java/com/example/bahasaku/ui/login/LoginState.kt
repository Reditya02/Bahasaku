package com.example.bahasaku.ui.login

import com.example.bahasaku.ui.register.AuthCondition

data class LoginState(
    val email: String = "",
    val password: String = "",

    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isLoginValid: Boolean = false,

    val authCondition: AuthCondition = AuthCondition.None
)
