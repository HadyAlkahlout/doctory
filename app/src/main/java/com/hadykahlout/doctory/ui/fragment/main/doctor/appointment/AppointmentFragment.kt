package com.hadykahlout.doctory.ui.fragment.main.doctor.appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentAppointmentBinding

class AppointmentFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppointmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardNotifications.setOnClickListener {
            findNavController().navigate(R.id.notificationsFragment)
        }

        binding.cardAddSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_appointment_to_addScheduleFragment)
        }

    }

}