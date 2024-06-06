package com.hadykahlout.doctory.model

data class Appointment(
    val id: Int,
    val image: String,
    val name: String,
    val category: String,
    val date: String,
    val time: String,
    val day: String,
    val timeFrom: String,
    val timeTo: String,
)
