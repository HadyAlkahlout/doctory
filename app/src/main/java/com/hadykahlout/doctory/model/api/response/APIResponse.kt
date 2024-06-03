package com.hadykahlout.doctory.model.api.response

import com.google.gson.annotations.SerializedName

data class APIResponse<T>(
    @SerializedName("status_code")
    val code: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)
