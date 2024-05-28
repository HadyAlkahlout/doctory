package com.hadykahlout.doctory.model

data class BookAppointment(
    val id: Int,
    val image: String,
    val name: String,
    val category: String,
    val date: String,
    val time: String,
)
