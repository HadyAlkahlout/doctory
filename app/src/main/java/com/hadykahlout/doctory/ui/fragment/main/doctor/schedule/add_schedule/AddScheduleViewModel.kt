package com.hadykahlout.doctory.ui.fragment.main.doctor.schedule.add_schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddScheduleViewModel : ViewModel() {

    val duration = MutableLiveData<Int>()

    init {
        duration.value = 0
    }
}