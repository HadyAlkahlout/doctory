package com.hadykahlout.doctory.ui.fragment.auth.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentMobileBinding
import com.hadykahlout.doctory.model.api.auth.ForgotPassword
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.auth.AuthViewModel

class MobileFragment : Fragment() {

    private lateinit var binding: FragmentMobileBinding
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMobileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isEmail = requireArguments().getBoolean("isEmail")

        if (isEmail){
            binding.llPhone.visibility = View.GONE
            binding.llEmail.visibility = View.VISIBLE
            binding.tvHint.text =
                getString(R.string.enter_your_email_we_will_send_a_verification_code_to_email)
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnContinue.setOnClickListener {
            if (isEmail && binding.etEmail.text.isEmpty()){
                binding.etEmail.error = getString(R.string.required)
            }else if (!(isEmail) && binding.etPhone.text.isEmpty()){
                binding.etPhone.error = getString(R.string.required)
            } else {
                forgotPassword()
            }
        }

    }

    private fun forgotPassword(){
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val forgot = ForgotPassword(
            email = requireArguments().getString("emailID") ?: ""
        )
        viewModel.forgotPassword(forgot)
        viewModel.forgotPasswordData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                val bundle = Bundle()
                bundle.putBoolean("isResetPass", true)

                if (requireArguments().getBoolean("isEmail")){
                    bundle.putString("emailID", binding.etEmail.text!!.trim().toString())
                    bundle.putBoolean("isMobile", false)
                } else {
                    bundle.putString("emailID", binding.etPhone.text!!.trim().toString())
                    bundle.putBoolean("isMobile", true)
                }

                findNavController().navigate(R.id.action_mobileFragment_to_verifyFragment, bundle)
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

}