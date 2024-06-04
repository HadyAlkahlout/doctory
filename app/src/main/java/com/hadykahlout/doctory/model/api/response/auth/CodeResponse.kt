package com.hadykahlout.doctory.model.api.response.auth

import com.google.gson.annotations.SerializedName

data class CodeResponse(
    @SerializedName("otp")
    val code: String
)