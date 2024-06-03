package com.hadykahlout.doctory.model.api.auth

import com.google.gson.annotations.SerializedName

data class ResetPassword (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm_password")
    val confirmPassword: String
)