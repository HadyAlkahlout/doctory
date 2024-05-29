package com.hadykahlout.doctory.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.ActivityPatientBinding

class PatientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}