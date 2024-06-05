package com.hadykahlout.doctory.ui.fragment.main.patient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadykahlout.doctory.data.PatientRepository
import com.hadykahlout.doctory.model.api.response.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PatientViewModel : ViewModel() {
    private val TAG = "HomeViewModel"

    private val repository = PatientRepository()

    val pastAppointmentsData = MutableLiveData<Response<APIResponse<Any>>>()
    val cancelAppointmentData = MutableLiveData<Response<APIResponse<Any>>>()

    fun getPastAppointments(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.pastAppointments()
            withContext(Dispatchers.Main){
                pastAppointmentsData.value = response
            }
        }
    }

    fun cancelAppointment(appointmentId: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.cancelAppointment(appointmentId)
            withContext(Dispatchers.Main){
                cancelAppointmentData.value = response
            }
        }
    }

}