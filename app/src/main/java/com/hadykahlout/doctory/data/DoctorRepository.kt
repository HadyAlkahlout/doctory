package com.hadykahlout.doctory.data

import com.hadykahlout.doctory.model.api.doctor.AddSchedule
import com.hadykahlout.doctory.network.ApiService

class DoctorRepository{

    suspend fun getSchedule() = ApiService.doctorClient.getSchedule()

    suspend fun addSchedule(
        addSchedule: AddSchedule
    ) = ApiService.doctorClient.addSchedule(addSchedule = addSchedule)

    suspend fun approveAppointment(
        appointmentId: String
    ) = ApiService.doctorClient.approveAppointment(appointmentId = appointmentId)

    suspend fun rejectAppointment(
        appointmentId: String
    ) = ApiService.doctorClient.rejectAppointment(appointmentId = appointmentId)

}
