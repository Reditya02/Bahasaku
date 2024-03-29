package com.example.bahasaku.ui.guest.register

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val retypePassword: String = "",

    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isRetypePasswordValid: Boolean = false,
    val isNameValid: Boolean = false,
    val isRegisterValid: Boolean = false,

    val isNotEmpty: Boolean = false,

    val authCondition: AuthCondition = AuthCondition.None,

    val isPasswordShown: Boolean = false,
    val isRetypePasswordShown: Boolean = false

)

enum class AuthCondition {
    None, Loading,
    Success,
    Failed, WrongFormat, NotRegistered, Empty, RetypePasswordNotSame
}
