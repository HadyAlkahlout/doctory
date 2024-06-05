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
import com.hadykahlout.doctory.adapter.doctor.PatientsAppointmentsAdapter
import com.hadykahlout.doctory.databinding.FragmentHomeBinding
import com.hadykahlout.doctory.model.doctor.PatientAppointment
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.MainViewModel

class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var patientsAppointmentsAdapter: PatientsAppointmentsAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val loading = LoadingDialog()

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
        getAppointments()
        binding.tvTotalPatientTitle.text = "${binding.tvTotalPatientTitle.text} July"

    }

    private fun getAppointments() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        patientsAppointmentsAdapter = PatientsAppointmentsAdapter(requireContext())

        val patientsAppointments = ArrayList<PatientAppointment>()

        viewModel.getAppointments()
        viewModel.appointmentsData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response
                    patientsAppointments.add(
                        PatientAppointment(0, "", "Sarah Bassam", "Backache", "8:00", "8:30")
                    )
                    patientsAppointments.add(
                        PatientAppointment(1, "", "Diana Bess", "Backache", "9:00", "9:30")
                    )
                } else {
                    Snackbar.make(
                        requireView(),
                        response.body()!!.message, Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        patientsAppointmentsAdapter.submitList(patientsAppointments)
        binding.rcPatients.adapter = patientsAppointmentsAdapter
    }
}