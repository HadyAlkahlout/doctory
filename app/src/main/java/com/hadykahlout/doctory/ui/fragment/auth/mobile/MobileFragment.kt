package com.hadykahlout.doctory.ui.fragment.auth.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentMobileBinding

class MobileFragment : Fragment() {

    private lateinit var binding: FragmentMobileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMobileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isResetPass = requireArguments().getBoolean("isResetPass")
        val isEmail = requireArguments().getBoolean("isEmail")

        if (isResetPass){
            binding.tvTitle.text = getString(R.string.reset_password)
            binding.tvHint.text =
                getString(R.string.enter_your_phone_number_we_will_send_a_verification_code_to_email)
        }

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
                binding.etEmail.error = "Required!!"
            }else if (!(isEmail) && binding.etPhone.text.isEmpty()){
                binding.etPhone.error = "Required!!"
            } else {
                val bundle = Bundle()

                if (isEmail){
                    bundle.putString("emailID", binding.etEmail.text!!.trim().toString())
                    bundle.putBoolean("isMobile", false)
                } else {
                    bundle.putString("emailID", binding.etPhone.text!!.trim().toString())
                    bundle.putBoolean("isMobile", true)
                }

                if (isResetPass){
                    bundle.putBoolean("isResetPass", true)
                }

                findNavController().navigate(R.id.action_mobileFragment_to_verifyFragment, bundle)
            }
        }

    }

}