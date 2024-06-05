package com.hadykahlout.doctory.model.doctor

data class Schedule(
    val id: Int,
    val image: String,
    val name: String,
    val category: String,
    val date: String,
    val day: String,
    val month: String,
    val time: String,
)
