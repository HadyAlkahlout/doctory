package com.hadykahlout.doctory.model

data class PatientAppointment(
    val id: Int,
    val image: String,
    val name: String,
    val category: String,
    val timeFrom: String,
    val timeTo: String,
)