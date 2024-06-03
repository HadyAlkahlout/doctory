package com.hadykahlout.doctory.ui.fragment.auth.new_pass

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentNewPassBinding
import com.hadykahlout.doctory.model.api.auth.ResetPassword
import com.hadykahlout.doctory.model.api.auth.VerifyEmail
import com.hadykahlout.doctory.ui.activity.AuthActivity
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.auth.AuthViewModel

class NewPassFragment : Fragment() {

    private lateinit var binding: FragmentNewPassBinding
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPassBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnResetPass.setOnClickListener {
            checkPass()
        }

        binding.btnDone.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), AuthActivity::class.java))
            requireActivity().finish()
        }

    }

    private fun checkPass() {
        if (binding.etPassword.text!!.isEmpty()){
            binding.passwordLayout.error = getString(R.string.required)
        } else if (binding.etConfirm.text!!.isEmpty()){
            binding.passwordLayout.error = null
            binding.confirmLayout.error = getString(R.string.required)
        } else if (binding.etConfirm.text!!.trim().toString() != binding.etPassword.text!!.trim().toString()){
            Log.e("TAG", "checkPass: Pass ${binding.etConfirm.text!!} != ${binding.etPassword.text!!}", )
            binding.confirmLayout.error =
                getString(R.string.confirm_password_do_not_match_the_new_password)
        } else {
            resetPassword()
        }
    }

    private fun resetPassword(){
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val resetPassword = ResetPassword(
            email = requireArguments().getString("emailID") ?: "",
            password = binding.etPassword.text.toString(),
            confirmPassword = binding.etConfirm.text.toString()
        )
        viewModel.resetPassword(resetPassword)
        viewModel.resetPasswordData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    binding.passwordLayout.error = null
                    binding.confirmLayout.error = null
                    binding.llPassword.visibility = View.GONE
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

}
