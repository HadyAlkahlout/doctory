package com.hadykahlout.doctory.ui.fragment.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentLoginBinding
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.auth.AuthViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    private val loading = LoadingDialog()

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
            binding.emailLayout.error = getString(R.string.required)
        } else if (binding.etPassword.text!!.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.required)
        } else {
            login()
        }
    }

    private fun login() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val loginUser = LoginUser(
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
        viewModel.login(loginUser)
        viewModel.loginData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    val bundle = Bundle()
                    bundle.putString("emailID", binding.etEmail.text!!.trim().toString())
                    bundle.putBoolean("isMobile", false)
                    findNavController().navigate(R.id.action_loginFragment_to_verifyFragment, bundle)
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