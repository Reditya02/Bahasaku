package com.example.bahasaku.ui.editprofile

data class EditProfileState(
    val name: String = "",
    val updateResult: UpdateResult = UpdateResult.NONE
)

enum class UpdateResult {
    SUCCESS, FAILED, NONE
}
