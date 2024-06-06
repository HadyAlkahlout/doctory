package com.hadykahlout.doctory.ui.fragment.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadykahlout.doctory.data.MainRepository
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.model.api.doctor.UpdatePassword
import com.hadykahlout.doctory.model.api.doctor.UpdateProfile
import com.hadykahlout.doctory.model.api.response.main.NotificationCount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"

    private val repository = MainRepository()

    val appointmentsData = MutableLiveData<Response<APIResponse<Any>>>()
    val profileData = MutableLiveData<Response<APIResponse<Any>>>()
    val deleteProfileData = MutableLiveData<Response<APIResponse<Any>>>()
    val updatePasswordData = MutableLiveData<Response<APIResponse<Any>>>()
    val updateProfileData = MutableLiveData<Response<APIResponse<Any>>>()
    val notificationData = MutableLiveData<Response<APIResponse<Any>>>()
    val countNotificationData = MutableLiveData<Response<APIResponse<NotificationCount>>>()

    fun getAppointments(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAppointments()
            withContext(Dispatchers.Main){
                appointmentsData.value = response
            }
        }
    }

    fun getProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getProfile()
            withContext(Dispatchers.Main){
                profileData.value = response
            }
        }
    }

    fun deleteProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.deleteProfile()
            withContext(Dispatchers.Main){
                deleteProfileData.value = response
            }
        }
    }

    fun updatePassword(updatePassword: UpdatePassword){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updatePassword(updatePassword)
            withContext(Dispatchers.Main){
                updatePasswordData.value = response
            }
        }
    }

    fun updateProfile(updateProfile: UpdateProfile){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.updateProfile(updateProfile)
            withContext(Dispatchers.Main){
                updateProfileData.value = response
            }
        }
    }

    fun getNotification(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getNotification()
            withContext(Dispatchers.Main){
                notificationData.value = response
            }
        }
    }

    fun countNotification(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.countNotification()
            withContext(Dispatchers.Main){
                countNotificationData.value = response
            }
        }
    }

}