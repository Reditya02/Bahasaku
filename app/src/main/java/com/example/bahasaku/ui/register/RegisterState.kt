package com.example.bahasaku.ui.register

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val retypePassword: String = "",

    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var isRetypePasswordValid: Boolean = false,
    var isNameValid: Boolean = false,
    var isRegisterValid: Boolean = false,


    val authCondition: AuthCondition = AuthCondition.None
)

enum class AuthCondition {
    None, Success, Failed
}
