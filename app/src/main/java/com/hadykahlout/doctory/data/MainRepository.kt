package com.hadykahlout.doctory.data

import com.hadykahlout.doctory.model.api.doctor.UpdatePassword
import com.hadykahlout.doctory.model.api.doctor.UpdateProfile
import com.hadykahlout.doctory.network.ApiService

class MainRepository{

    suspend fun getAppointments() = ApiService.mainClient.getAppointments()

    suspend fun getProfile() = ApiService.mainClient.getProfile()

    suspend fun deleteProfile() = ApiService.mainClient.getProfile()

    suspend fun updatePassword(
        updatePassword: UpdatePassword
    ) = ApiService.mainClient.updatePassword(updatePassword = updatePassword)

    suspend fun updateProfile(
        updateProfile: UpdateProfile
    ) = ApiService.mainClient.updateProfile(updateProfile = updateProfile)

    suspend fun getNotification() = ApiService.mainClient.getNotification()

    suspend fun countNotification() = ApiService.mainClient.countNotification()


}
