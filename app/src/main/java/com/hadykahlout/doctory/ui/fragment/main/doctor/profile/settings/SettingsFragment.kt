package com.hadykahlout.doctory.ui.fragment.main.doctor.profile.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentSettingsBinding
import com.hadykahlout.doctory.ui.activity.AuthActivity
import com.hadykahlout.doctory.utils.SharedPrefsHelper

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.llContactUS.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_contactUsFragment)
        }

        binding.llPrivacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_privacyPolicyFragment)
        }

        binding.llAboutUs.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_aboutUsFragment)
        }

        binding.llFAQ.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_FAQFragment)
        }

        binding.llLegal.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_legalFragment)
        }

        binding.tvLogout.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setTitle("Warning")
            dialogBuilder.setMessage("Do you want to logout?")
            dialogBuilder.setPositiveButton("No"){ dialog, id ->
                dialog.dismiss()
            }
            dialogBuilder.setNegativeButton("Yes"){ dialog, id ->
                logout()
            }
            dialogBuilder.setCancelable(false)
            val dialog = dialogBuilder.create()
            dialog.show()
        }

    }

    private fun logout() {
        SharedPrefsHelper.clearAll()
        requireActivity().startActivity(Intent(requireContext(), AuthActivity::class.java))
        requireActivity().finish()
    }

}