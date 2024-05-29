package com.hadykahlout.doctory.ui.fragment.main.doctor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.adapter.PatientsAppointmentsAdapter
import com.hadykahlout.doctory.databinding.FragmentHomeBinding
import com.hadykahlout.doctory.model.PatientAppointment


class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var patientsAppointmentsAdapter: PatientsAppointmentsAdapter

    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardNotifications.setOnClickListener {
            findNavController().navigate(R.id.notificationsFragment)
        }
        patientsAppointments()
        binding.tvTotalPatientTitle.text = "${binding.tvTotalPatientTitle.text} July"

    }

    private fun patientsAppointments() {
        patientsAppointmentsAdapter =
            PatientsAppointmentsAdapter(requireContext()) { patient: PatientAppointment ->
                Snackbar.make(requireView(), "Chat with ${patient.name}", Snackbar.LENGTH_SHORT)
                    .show()
            }

        val patientsAppointments = ArrayList<PatientAppointment>()
        patientsAppointments.add(
            PatientAppointment(0, "", "Sarah Bassam", "Backache", "8:00", "8:30")
        )
        patientsAppointments.add(
            PatientAppointment(1, "", "Diana Bess", "Backache", "9:00", "9:30")
        )

        patientsAppointmentsAdapter.submitList(patientsAppointments)
        binding.rcPatients.adapter = patientsAppointmentsAdapter
    }
}