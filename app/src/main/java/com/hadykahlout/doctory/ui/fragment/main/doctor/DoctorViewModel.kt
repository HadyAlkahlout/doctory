package com.hadykahlout.doctory.ui.fragment.main.doctor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadykahlout.doctory.data.DoctorRepository
import com.hadykahlout.doctory.model.api.doctor.AddSchedule
import com.hadykahlout.doctory.model.api.response.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DoctorViewModel : ViewModel() {
    private val TAG = "HomeViewModel"

    private val repository = DoctorRepository()

    val schedulesData = MutableLiveData<Response<APIResponse<Any>>>()
    val addData = MutableLiveData<Response<APIResponse<Any>>>()
    val approveData = MutableLiveData<Response<APIResponse<Any>>>()
    val rejectData = MutableLiveData<Response<APIResponse<Any>>>()

    fun getSchedule(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSchedule()
            withContext(Dispatchers.Main){
                schedulesData.value = response
            }
        }
    }

    fun addSchedule(addSchedule: AddSchedule){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.addSchedule(addSchedule)
            withContext(Dispatchers.Main){
                addData.value = response
            }
        }
    }

    fun approveAppointment(appointmentId: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.approveAppointment(appointmentId)
            withContext(Dispatchers.Main){
                approveData.value = response
            }
        }
    }

    fun rejectAppointment(appointmentId: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.rejectAppointment(appointmentId)
            withContext(Dispatchers.Main){
                rejectData.value = response
            }
        }
    }


}