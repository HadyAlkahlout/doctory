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
import com.hadykahlout.doctory.adapter.doctor.TodayAppointmentAdapter
import com.hadykahlout.doctory.databinding.FragmentHomeBinding
import com.hadykahlout.doctory.model.Appointment
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.MainViewModel

class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var todayAppointmentsAdapter: TodayAppointmentAdapter

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
            val bundle = Bundle()
            bundle.putBoolean("isDoctor", true)
            findNavController().navigate(R.id.notificationsFragment, bundle)
        }
        getAppointments()
//        getNotificationCount()
        binding.tvTotalPatientTitle.text = "${binding.tvTotalPatientTitle.text} July"

    }

    private fun getAppointments() {
//        loading.show(requireActivity().supportFragmentManager, "Loading")
        todayAppointmentsAdapter = TodayAppointmentAdapter(requireContext())

        val appointments = ArrayList<Appointment>()

        appointments.add(
            Appointment(0, "", "Sarah Bassam", "Backache", "8", "8:30", "Mon" , "08:00", "08:30")
        )
        appointments.add(
            Appointment(1, "", "Diana Bess", "Backache", "9", "9:30", "Tues", "09:00", "09:30")
        )

//        viewModel.getAppointments()
//        viewModel.appointmentsData.observe(viewLifecycleOwner){ response ->
//            loading.dismiss()
//            if (response.body() != null) {
//                if (response.body()!!.status && response.body()!!.code in 200..299){
//                    // Here handel the request response
//
//                    appointments.add(
//                        Appointment(0, "", "Sarah Bassam", "Backache", "8:00", "8:30")
//                    )
//                    appointments.add(
//                        Appointment(1, "", "Diana Bess", "Backache", "9:00", "9:30")
//                    )
//
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

        todayAppointmentsAdapter.submitList(appointments)
        binding.rcPatients.adapter = todayAppointmentsAdapter
    }

    private fun getNotificationCount() {
        viewModel.countNotification()
        viewModel.countNotificationData.observe(viewLifecycleOwner){ response ->
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response

                    if (response.body()!!.data!!.count == 0) {
                        binding.cardCount.visibility = View.GONE
                    } else {
                        binding.cardCount.visibility = View.VISIBLE
                    }

                } else {
                    binding.cardCount.visibility = View.GONE
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