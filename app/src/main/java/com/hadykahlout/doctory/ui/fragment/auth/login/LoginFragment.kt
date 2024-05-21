package com.hadykahlout.doctory.ui.fragment.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.googleAuth.setOnClickListener {
            Snackbar.make(requireView(), "Go Google Authentication", Snackbar.LENGTH_SHORT).show()
        }

        binding.tvForgot.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnSignIn.setOnClickListener {
            checkSignIn()
        }

    }

    private fun checkSignIn() {
        if (binding.etEmail.text!!.isEmpty()) {
            binding.emailLayout.error = "Required!!"
        } else if (binding.etPassword.text!!.isEmpty()) {
            binding.passwordLayout.error = "Required!!"
        } else {
            val bundle = Bundle()
            bundle.putBoolean("isResetPass", false)
            bundle.putBoolean("isEmail", false)
            findNavController().navigate(R.id.action_loginFragment_to_mobileFragment, bundle)
        }
    }

}