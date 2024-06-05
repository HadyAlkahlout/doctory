package com.hadykahlout.doctory.ui.fragment.main.patient.appointment

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.adapter.patient.AppointmentAdapter
import com.hadykahlout.doctory.adapter.patient.PastAppointmentAdapter
import com.hadykahlout.doctory.databinding.FragmentAppointmentsBinding
import com.hadykahlout.doctory.model.patient.Appointment
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.MainViewModel
import com.hadykahlout.doctory.ui.fragment.main.patient.PatientViewModel

class AppointmentsFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsBinding

    private val patientViewModel by lazy {
        ViewModelProvider(this)[PatientViewModel::class.java]
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val loading = LoadingDialog()

    private lateinit var appointmentAdapter: AppointmentAdapter
    private val allRequests = ArrayList<Appointment>()

    private lateinit var pastAppointmentAdapter: PastAppointmentAdapter
    private val pastAppointments = ArrayList<Appointment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppointmentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appointmentAdapter = AppointmentAdapter(requireContext()){ appointment ->
            // Cancel Request
            cancelAppointment(appointment.id)
        }
        appointmentAdapter.submitList(allRequests)
        binding.rcRequests.layoutManager = LinearLayoutManager(requireContext())
        binding.rcRequests.adapter = appointmentAdapter

        getAllRequests()

        pastAppointmentAdapter = PastAppointmentAdapter(requireContext())
        pastAppointmentAdapter.submitList(allRequests)
        binding.rcPastAppointments.layoutManager = LinearLayoutManager(requireContext())
        binding.rcPastAppointments.adapter = pastAppointmentAdapter

        binding.cardPastAppointments.setOnClickListener {
            binding.cardPastAppointments.setCardBackgroundColor(requireActivity().getColor(R.color.white))
            binding.tvPastAppointments.setTypeface(binding.tvPastAppointments.typeface, Typeface.BOLD)
            binding.rcPastAppointments.visibility = View.VISIBLE
            binding.cardAllRequest.setCardBackgroundColor(requireActivity().getColor(android.R.color.transparent))
            binding.tvAllRequest.setTypeface(binding.tvPastAppointments.typeface, Typeface.NORMAL)
            binding.rcRequests.visibility = View.GONE
            getPastAppointment()
        }

        binding.cardAllRequest.setOnClickListener {
            binding.cardAllRequest.setCardBackgroundColor(requireActivity().getColor(R.color.white))
            binding.tvAllRequest.setTypeface(binding.tvPastAppointments.typeface, Typeface.BOLD)
            binding.rcRequests.visibility = View.VISIBLE
            binding.cardPastAppointments.setCardBackgroundColor(requireActivity().getColor(android.R.color.transparent))
            binding.tvPastAppointments.setTypeface(binding.tvPastAppointments.typeface, Typeface.NORMAL)
            binding.rcPastAppointments.visibility = View.GONE
            getAllRequests()
        }

    }

    private fun cancelAppointment(id: Int) {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        patientViewModel.cancelAppointment(id.toString())
        patientViewModel.cancelAppointmentData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response
                    Snackbar.make(
                        requireView(),
                        response.body()!!.message, Snackbar.LENGTH_SHORT
                    ).show()
                    getAllRequests()

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

    private fun getPastAppointment() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        patientViewModel.getPastAppointments()
        patientViewModel.pastAppointmentsData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response
                    pastAppointments.clear()

                    pastAppointments.add(
                        Appointment(
                            0, "", "Vandarani Aduhai", "Backache", "Wed Jun 21", "8:00 AM"
                        )
                    )

                    pastAppointments.add(
                        Appointment(
                            1, "", "Vandarani Aduhai", "Backache", "Wed Jun 21", "8:00 AM"
                        )
                    )

                    pastAppointmentAdapter.notifyDataSetChanged()
                    binding.rcRequests.visibility = View.GONE
                    binding.rcPastAppointments.visibility = View.VISIBLE
                    binding.llEmptyData.visibility = View.GONE

                } else {
                    Snackbar.make(
                        requireView(),
                        response.body()!!.message, Snackbar.LENGTH_SHORT
                    ).show()
                    binding.rcPastAppointments.visibility = View.GONE
                    binding.llEmptyData.visibility = View.VISIBLE
                }
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT
                ).show()
                binding.rcPastAppointments.visibility = View.GONE
                binding.llEmptyData.visibility = View.VISIBLE
            }
        }
    }

    private fun getAllRequests() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.getAppointments()
        viewModel.appointmentsData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response
                    allRequests.clear()

                    allRequests.add(
                        Appointment(
                            0, "", "Vandarani Aduhai", "Backache", "Wed Jun 21", "8:00 AM"
                        )
                    )

                    allRequests.add(
                        Appointment(
                            1, "", "Vandarani Aduhai", "Backache", "Wed Jun 21", "8:00 AM"
                        )
                    )

                    appointmentAdapter.notifyDataSetChanged()
                    binding.rcRequests.visibility = View.VISIBLE
                    binding.rcPastAppointments.visibility = View.GONE
                    binding.llEmptyData.visibility = View.GONE

                } else {
                    Snackbar.make(
                        requireView(),
                        response.body()!!.message, Snackbar.LENGTH_SHORT
                    ).show()
                    binding.rcRequests.visibility = View.GONE
                    binding.llEmptyData.visibility = View.VISIBLE
                }
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT
                ).show()
                binding.rcRequests.visibility = View.GONE
                binding.llEmptyData.visibility = View.VISIBLE
            }
        }
    }

}