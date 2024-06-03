package com.hadykahlout.doctory.ui.fragment.auth.verify

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
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentVerifyBinding
import com.hadykahlout.doctory.model.api.auth.ForgotPassword
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.model.api.auth.VerifyEmail
import com.hadykahlout.doctory.model.api.auth.VerifyResetCode
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.activity.PatientActivity
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.auth.AuthViewModel

class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentVerifyBinding
    private val verifyViewModel: VerifyViewModel by lazy {
        ViewModelProvider(this)[VerifyViewModel::class.java]
    }
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verifyViewModel.startTimer()

        binding.tvEmail.text = requireArguments().getString("emailID")

        if (requireArguments().getBoolean("isMobile")) {
            binding.tvVerfiyType.text = requireActivity().getString(R.string.phone_verify)
        }

        verifyViewModel.timer.observe(viewLifecycleOwner) { time ->
            binding.tvTimer.text = time
        }

        verifyViewModel.resendVisibility.observe(viewLifecycleOwner) { status ->
            binding.tvResend.visibility = if (status) View.VISIBLE else View.GONE
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvResend.setOnClickListener {
            resendCode()
        }

        binding.btnDone.setOnClickListener {
            done()
        }

        binding.btnContinue.setOnClickListener {
            if (binding.spfPIN.text!!.isEmpty()) {
                binding.spfPIN.error = getString(R.string.required)
                Snackbar.make(
                    requireView(),
                    getString(R.string.verify_code_is_required), Snackbar.LENGTH_SHORT
                )
                    .setTextColor(requireActivity().getColor(R.color.required))
                    .show()
            }else if(binding.spfPIN.text!!.toString() == requireArguments().getString("code")) {
                Snackbar.make(
                    requireView(),
                    getString(R.string.incorrect_code), Snackbar.LENGTH_SHORT
                )
                    .setTextColor(requireActivity().getColor(R.color.required))
                    .show()
            } else {

                if (requireArguments().getBoolean("isResetPass")) {
                    verifyResetCode()
                } else {
                    verifyCode()
                }
            }
        }

    }

    private fun verifyCode() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val verify = VerifyEmail(
            email = requireArguments().getString("emailID") ?: "",
            code = binding.spfPIN.text.toString()
        )
        viewModel.verifyEmail(verify)
        viewModel.verifyEmailData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.code_verified_successfully), Toast.LENGTH_SHORT
                    )
                        .show()
                    binding.llCode.visibility = View.GONE
                    binding.llSuccessful.visibility = View.VISIBLE
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

    private fun verifyResetCode() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val verify = VerifyResetCode(
            email = requireArguments().getString("emailID") ?: "",
            resetCode = binding.spfPIN.text.toString()
        )
        viewModel.verifyResetCode(verify)
        viewModel.verifyResetCodeData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.code_verified_successfully), Toast.LENGTH_SHORT
                    )
                        .show()
                    findNavController().navigate(R.id.action_verifyFragment_to_newPassFragment)
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

    private fun resendCode() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val resend = ForgotPassword(
            email = requireArguments().getString("emailID") ?: ""
        )
        viewModel.resendVerification(resend)
        viewModel.resendVerificationData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    verifyViewModel.startTimer()
                    Snackbar.make(
                        requireView(),
                        getString(R.string.we_resent_you_the_code), Snackbar.LENGTH_SHORT
                    ).show()
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
        requireActivity().startActivity(Intent(requireContext(), DoctorActivity::class.java))
//        requireActivity().startActivity(Intent(requireContext(), PatientActivity::class.java))
        requireActivity().finish()
    }

}