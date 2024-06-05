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
import com.hadykahlout.doctory.adapter.doctor.ScheduleAdapter
import com.hadykahlout.doctory.databinding.FragmentScheduleBinding
import com.hadykahlout.doctory.model.doctor.Schedule
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.doctor.DoctorViewModel

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var adapter: ScheduleAdapter
    private val schedules = ArrayList<Schedule>()

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

        binding.cardNotifications.setOnClickListener {
            findNavController().navigate(R.id.notificationsFragment)
        }

        binding.cardAddSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_schedule_to_addScheduleFragment)
        }

        adapter = ScheduleAdapter(
            requireContext(),
            { schedule ->
                onAcceptSchedule(schedule.id)
            },
            { schedule ->
                onRejectSchedule(schedule.id)
            })
        adapter.submitList(schedules)
        binding.rcSchedule.adapter = adapter

        viewModel.schedulesData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    // Here handel the request response
                    schedules.add(
                        Schedule(0, "", "Sarah Bassam", "Backache", "13", "Mon", "Oct", "8:00")
                    )
                    schedules.add(
                        Schedule(1, "", "Diana Bess", "Backache", "13", "Mon", "Oct", "9:00")
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
        getSchedule()

    }

    private fun getSchedule() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.getSchedule()
        adapter.notifyDataSetChanged()
    }

    private fun onAcceptSchedule(id: Int) {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.approveAppointment(id.toString())
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

    private fun onRejectSchedule(id: Int) {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.rejectAppointment(id.toString())
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