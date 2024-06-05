package com.hadykahlout.doctory.ui.fragment.main.doctor.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentProfileBinding
import com.hadykahlout.doctory.utils.SharedPrefsHelper

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        getProfileData()

    }

    private fun setListeners() {

        binding.llAccount.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isDoctor", true)
            findNavController().navigate(R.id.action_navigation_profile_to_accountFragment2, bundle)
        }

        binding.llEducation.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_educationFragment)
        }

        binding.llLicense.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_medicalLicenseFragment)
        }

        binding.llSpeciality.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_specialityListFragment)
        }

        binding.llSettings.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_settingsFragment)
        }

    }

    private fun getProfileData() {
        val user = SharedPrefsHelper.getServerUser()
        if (user.avatar != null && user.avatar != "") {
            Glide
                .with(requireContext())
                .load(user.avatar)
                .into(binding.imgDoctor)
        }
        binding.tvName.text = user.fullName
        binding.tvCategory.text = user.email
    }

}