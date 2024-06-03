package com.hadykahlout.doctory.model.api.auth

import com.google.gson.annotations.SerializedName

data class VerifyResetCode(
    @SerializedName("email")
    val email: String,
    @SerializedName("resetCode")
    val resetCode: String
)
