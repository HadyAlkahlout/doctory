package com.hadykahlout.doctory.model.api.doctor

import com.google.gson.annotations.SerializedName

data class UpdatePassword(
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("new_password")
    val newPassword: String,
    @SerializedName("confirm_password")
    val confirmPassword: String,
)
