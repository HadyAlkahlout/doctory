package com.hadykahlout.doctory.ui.fragment.auth.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.googleAuth.setOnClickListener {
            Snackbar.make(requireView(), "Go Google Authentication", Snackbar.LENGTH_SHORT).show()
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSignUp.setOnClickListener {
            checkSignUp()
        }

    }

    private fun checkSignUp() {
        if (binding.etUsername.text!!.isEmpty()) {
            binding.usernameLayout.error = "Required!!"
        } else if (binding.etEmail.text!!.isEmpty()) {
            binding.emailLayout.error = "Required!!"
        } else if (binding.etPassword.text!!.isEmpty()) {
            binding.passwordLayout.error = "Required!!"
        } else if (!(binding.cbTerms.isChecked)) {
            Snackbar.make(
                requireView(),
                "You must agree to our terms of service and privacy policy to make an account!!",
                Snackbar.LENGTH_SHORT
            )
                .setTextColor(requireActivity().getColor(R.color.required))
                .show()
        } else {
            val bundle = Bundle()
            bundle.putString("emailID", binding.etEmail.text!!.trim().toString())
            bundle.putBoolean("isMobile", false)
            findNavController().navigate(R.id.action_signUpFragment_to_verifyFragment, bundle)
        }
    }

}