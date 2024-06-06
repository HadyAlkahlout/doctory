package com.hadykahlout.doctory.ui.fragment.main.doctor.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.adapter.doctor.MonthAppointmentsAdapter
import com.hadykahlout.doctory.databinding.FragmentScheduleBinding
import com.hadykahlout.doctory.model.Appointment
import com.hadykahlout.doctory.model.MonthAppointment
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.doctor.DoctorViewModel

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var adapter: MonthAppointmentsAdapter
    private val monthAppointments = ArrayList<MonthAppointment>()

    private val viewModel by lazy {
        ViewModelProvider(this)[DoctorViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardAddSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_schedule_to_addScheduleFragment)
        }

        adapter = MonthAppointmentsAdapter(
            requireContext(),
            { id ->
                onAcceptSchedule(id)
            },
            { id ->
                onRejectSchedule(id)
            })
        adapter.submitList(monthAppointments)
        binding.rcSchedule.adapter = adapter

        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(0, "", "Sarah Bassam", "Backache", "8", "8:30", "Mon" , "08:00", "08:30")
        )
        appointments.add(
            Appointment(1, "", "Diana Bess", "Backache", "9", "9:30", "Tues", "09:00", "09:30")
        )
        monthAppointments.add(
            MonthAppointment("July 2022", appointments)
        )
        monthAppointments.add(
            MonthAppointment("August 2022", appointments)
        )
        adapter.notifyDataSetChanged()

//        viewModel.schedulesData.observe(viewLifecycleOwner) { response ->
//            loading.dismiss()
//            if (response.body() != null) {
//                if (response.body()!!.status && response.body()!!.code in 200..299) {
//                    // Here handel the request response
//                    val appointments = ArrayList<Appointment>()
//                    appointments.add(
//                        Appointment(0, "", "Sarah Bassam", "Backache", "8", "8:30", "Mon" , "08:00", "08:30")
//                    )
//                    appointments.add(
//                        Appointment(1, "", "Diana Bess", "Backache", "9", "9:30", "Tues", "09:00", "09:30")
//                    )
//                    monthAppointments.add(
//                        MonthAppointment("July 2022", appointments)
//                    )
//                    monthAppointments.add(
//                        MonthAppointment("August 2022", appointments)
//                    )
//                    adapter.notifyDataSetChanged()
//                } else {
//                    Snackbar.make(
//                        requireView(),
//                        response.body()!!.message, Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//            } else {
//                Snackbar.make(
//                    requireView(),
//                    getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT
//                ).show()
//            }
//        }
//        getSchedule()

    }

    private fun getSchedule() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.getSchedule()
        adapter.notifyDataSetChanged()
    }

    private fun onAcceptSchedule(id: String) {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.approveAppointment(id)
        viewModel.approveData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    // Here handel the request response
                    Snackbar.make(requireView(),
                        getString(R.string.appointment_approved_successfully), Snackbar.LENGTH_SHORT).show()
                    getSchedule()
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
    }

    private fun onRejectSchedule(id: String) {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.rejectAppointment(id)
        viewModel.rejectData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    // Here handel the request response
                    Snackbar.make(requireView(),
                        getString(R.string.appointment_rejected_successfully), Snackbar.LENGTH_SHORT).show()
                    getSchedule()
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
    }

}