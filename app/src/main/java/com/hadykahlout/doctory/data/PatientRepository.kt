package com.hadykahlout.doctory.data

import com.hadykahlout.doctory.network.ApiService

class PatientRepository{

    suspend fun pastAppointments() = ApiService.patientClient.pastAppointments()

    suspend fun cancelAppointment(
        appointmentId: String
    ) = ApiService.patientClient.cancelAppointment(appointmentId = appointmentId)

}
