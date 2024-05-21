package com.hadykahlout.doctory.ui.fragment.auth.verify

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VerifyViewModel : ViewModel() {

    val timer = MutableLiveData<String>()
    val resendVisibility = MutableLiveData<Boolean>()

    fun startTimer() {
        resendVisibility.value = false
        object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer.value = (millisUntilFinished/ 1000).toString()
            }

            override fun onFinish() {
                resendVisibility.value = true
            }
        }.start()
    }

}