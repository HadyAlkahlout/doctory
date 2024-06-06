package com.hadykahlout.doctory.model

data class MonthAppointment(
    val month: String,
    val appointments: ArrayList<Appointment>
)
