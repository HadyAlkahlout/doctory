package com.hadykahlout.doctory.ui.fragment.auth.sign_up

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentSignUpBinding
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.model.api.auth.SignUpUser
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.auth.AuthViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    private val loading = LoadingDialog()
    private var passCheck = false

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

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.llChar.visibility = View.VISIBLE
                binding.llNum.visibility = View.VISIBLE
                binding.llUper.visibility = View.VISIBLE
                checkPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        })

    }

    private fun checkPassword(pass: String) {
        var hasLower = false
        var hasUper = false
        if (pass.length >= 8) {
            binding.imgCharCorrect.visibility = View.VISIBLE
            binding.imgCharError.visibility = View.GONE
        } else {
            binding.imgCharCorrect.visibility = View.GONE
            binding.imgCharError.visibility = View.VISIBLE
        }
        pass.toCharArray().forEach {
            if (it.isDigit()) {
                binding.imgNumCorrect.visibility = View.VISIBLE
                binding.imgNumError.visibility = View.GONE
                return@forEach
            } else {
                binding.imgNumCorrect.visibility = View.GONE
                binding.imgNumError.visibility = View.VISIBLE
            }
        }
        pass.toCharArray().forEach {
            if (it.isUpperCase()) {
                hasUper = true
                return@forEach
            }
        }
        pass.toCharArray().forEach {
            if (it.isLowerCase()) {
                hasLower = true
                return@forEach
            }
        }
        if (hasUper && hasLower) {
            binding.imgUperCorrect.visibility = View.VISIBLE
            binding.imgUperError.visibility = View.GONE
        } else {
            binding.imgUperCorrect.visibility = View.GONE
            binding.imgUperError.visibility = View.VISIBLE
        }
        if (hasUper && hasLower && binding.imgCharCorrect.isVisible && binding.imgNumCorrect.isVisible && binding.imgUperCorrect.isVisible) {
            binding.llChar.visibility = View.GONE
            binding.llNum.visibility = View.GONE
            binding.llUper.visibility = View.GONE
            binding.llStrongPass.visibility = View.VISIBLE
            passCheck = true
        }
    }

    private fun checkSignUp() {
        if (binding.etUsername.text!!.isEmpty()) {
            binding.usernameLayout.error = getString(R.string.required)
        } else if (binding.etEmail.text!!.isEmpty()) {
            binding.emailLayout.error = getString(R.string.required)
        } else if (binding.etPassword.text!!.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.required)
        } else if (passCheck) {
            binding.passwordLayout.error = ""
        } else if (!(binding.cbTerms.isChecked)) {
            Snackbar.make(
                requireView(),
                getString(R.string.you_must_agree_to_our_terms_of_service_and_privacy_policy_to_make_an_account),
                Snackbar.LENGTH_SHORT
            )
                .setTextColor(requireActivity().getColor(R.color.required))
                .show()
        } else {
            signUp()
        }
    }

    private fun signUp() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val signUpUser = SignUpUser(
            username = binding.etUsername.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString(),
            confirmPassword = binding.etPassword.text.toString(),
            agreement = binding.cbTerms.isChecked
        )
        viewModel.signUp(signUpUser)
        viewModel.signUpData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    val bundle = Bundle()
                    bundle.putString("emailID", binding.etEmail.text!!.trim().toString())
                    bundle.putBoolean("isMobile", false)
                    findNavController().navigate(
                        R.id.action_signUpFragment_to_verifyFragment,
                        bundle
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
    }

}