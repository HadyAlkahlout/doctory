package com.hadykahlout.doctory.network

import APP_LANGUAGE
import SERVER_TOKEN
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.utils.SharedPrefsHelper
import retrofit2.Response
import retrofit2.http.*
interface PatientApiClient {

    @GET("appointments/past")
    suspend fun pastAppointments(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @DELETE("appointments/{id}/cancel")
    suspend fun cancelAppointment(
        @Path("id") appointmentId: String,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

}