package com.hadykahlout.doctory.ui.fragment.main.doctor.appointment.add_schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddScheduleViewModel : ViewModel() {

    val startTimeStatus = MutableLiveData<Int>()
    val endTimeStatus = MutableLiveData<Int>()
    val duration = MutableLiveData<Int>()

    init {
        startTimeStatus.value = 0
        endTimeStatus.value = 0
        duration.value = 0
    }
}