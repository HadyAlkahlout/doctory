package com.hadykahlout.doctory.model.api.response.auth

import com.google.gson.annotations.SerializedName

data class ResetCodeResponse(
    @SerializedName("reset_code")
    val code: String
)