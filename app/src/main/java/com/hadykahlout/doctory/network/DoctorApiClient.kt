package com.hadykahlout.doctory.network

import APP_LANGUAGE
import SERVER_TOKEN
import com.hadykahlout.doctory.model.api.doctor.AddSchedule
import com.hadykahlout.doctory.model.api.doctor.UpdatePassword
import com.hadykahlout.doctory.model.api.doctor.UpdateProfile
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.utils.SharedPrefsHelper
import retrofit2.Response
import retrofit2.http.*
interface DoctorApiClient {

    @GET("schedule")
    suspend fun getSchedule(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @POST("schedule")
    suspend fun addSchedule(
        @Body addSchedule: AddSchedule,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @PUT("appointments/{id}/approve")
    suspend fun approveAppointment(
        @Path("id") appointmentId: String,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @PUT("appointments/{id}/reject")
    suspend fun rejectAppointment(
        @Path("id") appointmentId: String,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

}