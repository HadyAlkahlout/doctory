package com.hadykahlout.doctory.model.api.response.main

import com.google.gson.annotations.SerializedName

data class NotificationCount (
    @SerializedName("count")
    val count: Int
)