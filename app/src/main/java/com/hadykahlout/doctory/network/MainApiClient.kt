package com.hadykahlout.doctory.network

import APP_LANGUAGE
import SERVER_TOKEN
import com.hadykahlout.doctory.model.api.doctor.AddSchedule
import com.hadykahlout.doctory.model.api.doctor.UpdatePassword
import com.hadykahlout.doctory.model.api.doctor.UpdateProfile
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.model.api.response.main.NotificationCount
import com.hadykahlout.doctory.utils.SharedPrefsHelper
import retrofit2.Response
import retrofit2.http.*
interface MainApiClient {

    @GET("appointments")
    suspend fun getAppointments(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @GET("users/profile")
    suspend fun getProfile(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @DELETE("users/profile")
    suspend fun deleteProfile(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @PUT("users/profile/password")
    suspend fun updatePassword(
        @Body updatePassword: UpdatePassword,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @PUT("users/profile")
    suspend fun updateProfile(
        @Body updateProfile: UpdateProfile,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @GET("notifications")
    suspend fun getNotification(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<Any>>

    @GET("notifications/unread-count")
    suspend fun countNotification(
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
        @Header("Authorization") token: String = SharedPrefsHelper.getString(SERVER_TOKEN, "")
    ): Response<APIResponse<NotificationCount>>

}