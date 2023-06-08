package com.example.bahasaku.ui.editprofile

data class EditProfileState(
    val name: String = "",
    val updateResult: UpdateResult = UpdateResult.NONE,
    val isLoading: Boolean = false
)

enum class UpdateResult {
    SUCCESS, FAILED, NONE
}
