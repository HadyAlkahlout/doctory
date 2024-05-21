package com.hadykahlout.doctory.ui.fragment.auth.forgot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentForgotBinding

class ForgotFragment : Fragment() {

    private lateinit var binding: FragmentForgotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val bundle = Bundle()

        binding.cardEmail.setOnClickListener {
            bundle.putBoolean("isResetPass", true)
            bundle.putBoolean("isEmail", true)
            findNavController().navigate(R.id.action_forgotFragment_to_mobileFragment, bundle)
        }

        binding.cardPhone.setOnClickListener {
            bundle.putBoolean("isResetPass", true)
            bundle.putBoolean("isEmail", false)
            findNavController().navigate(R.id.action_forgotFragment_to_mobileFragment, bundle)
        }

    }

}