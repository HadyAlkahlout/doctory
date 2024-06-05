package com.hadykahlout.doctory.ui.fragment.auth.login

import SERVER_TOKEN
import SERVER_USER
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentLoginBinding
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.activity.PatientActivity
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.auth.AuthViewModel
import com.hadykahlout.doctory.utils.SharedPrefsHelper

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
            Snackbar.make(
                requireView(),
                getString(R.string.this_feature_will_be_available_soon),
                Snackbar.LENGTH_SHORT
            ).show()
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
                    val gson = Gson()
                    val serverUser = gson.toJson(response.body()!!.data)
                    SharedPrefsHelper.save(SERVER_USER, serverUser)
                    SharedPrefsHelper.save(SERVER_TOKEN, "bearer ${response.body()!!.data!!.accessToken}")
                    done()
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

    private fun done() {
        val serverUser = SharedPrefsHelper.getServerUser()
        if (serverUser.roleId == 2){
            requireActivity().startActivity(Intent(requireContext(), DoctorActivity::class.java))
        } else if (serverUser.roleId == 3){
            requireActivity().startActivity(Intent(requireContext(), PatientActivity::class.java))
        } else if (serverUser.roleId == 1){
            Toast.makeText(requireContext(),
                getString(R.string.please_open_your_account_using_the_dashboard), Toast.LENGTH_SHORT).show()
        }
        requireActivity().finish()
    }

}