package com.hadykahlout.doctory.model

data class Notification(
    val id: Int,
    val image: String,
    val title: String,
    val details: String,
    val date: String,
    val time: String,
)
