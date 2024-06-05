package com.hadykahlout.doctory.model.api.doctor

import com.google.gson.annotations.SerializedName

data class AddSchedule(
    @SerializedName("days")
    val days: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("duration")
    val duration: String
)
