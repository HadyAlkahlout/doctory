package com.hadykahlout.doctory.ui.fragment.main.doctor.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardNotifications.setOnClickListener {
            findNavController().navigate(R.id.notificationsFragment)
        }

        binding.cardAddSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_schedule_to_addScheduleFragment)
        }

    }

}