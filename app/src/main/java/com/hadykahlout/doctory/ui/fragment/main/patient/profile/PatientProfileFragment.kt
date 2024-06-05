package com.hadykahlout.doctory.ui.fragment.main.patient.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentPatientProfileBinding
import com.hadykahlout.doctory.utils.SharedPrefsHelper

class PatientProfileFragment : Fragment() {

    private lateinit var binding: FragmentPatientProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = SharedPrefsHelper.getServerUser()
        if (user.avatar != null && user.avatar != "") {
            Glide
                .with(requireContext())
                .load(user.avatar)
                .into(binding.imgPatient)
        }
        binding.tvName.text = user.fullName
        binding.tvPulse.text = "100"
        binding.tvWeight.text = "75"

        binding.llAccount.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isDoctor", false)
            findNavController().navigate(R.id.action_navigation_patient_profile_to_accountFragment, bundle)
        }

        binding.llInsurance.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_patient_profile_to_insuranceFragment)
        }

    }

}