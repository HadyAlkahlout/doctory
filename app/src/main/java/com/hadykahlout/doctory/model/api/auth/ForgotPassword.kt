package com.hadykahlout.doctory.model.api.auth

import com.google.gson.annotations.SerializedName

data class ForgotPassword(
    @SerializedName("email")
    val email: String
)
