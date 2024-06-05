package com.hadykahlout.doctory.model.patient

data class Appointment(
    val id: Int,
    val image: String,
    val name: String,
    val category: String,
    val date: String,
    val time: String,
)
