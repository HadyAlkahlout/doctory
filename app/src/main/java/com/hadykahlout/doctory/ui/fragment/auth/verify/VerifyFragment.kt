package com.hadykahlout.doctory.ui.fragment.auth.verify

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.hadykahlout.doctory.ui.activity.MainActivity

class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentVerifyBinding
    private val viewModel: VerifyViewModel by lazy {
        ViewModelProvider(this)[VerifyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startTimer()

        binding.tvEmail.text = requireArguments().getString("emailID")

        if (requireArguments().getBoolean("isMobile")) {
            binding.tvVerfiyType.text = requireActivity().getString(R.string.phone_verify)
        }

        viewModel.timer.observe(viewLifecycleOwner){time ->
            binding.tvTimer.text = time
        }

        viewModel.resendVisibility.observe(viewLifecycleOwner){status ->
            binding.tvResend.visibility = if (status) View.VISIBLE else View.GONE
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvResend.setOnClickListener {
            viewModel.startTimer()
        }

        binding.btnDone.setOnClickListener {
            done()
        }

        binding.btnContinue.setOnClickListener {
            if (binding.spfPIN.text!!.isEmpty()) {
                binding.spfPIN.error = "Required!!"
                Snackbar.make(requireView(), "Verify code is required!!", Snackbar.LENGTH_SHORT)
                    .setTextColor(requireActivity().getColor(R.color.required))
                    .show()
            } else {
                Toast.makeText(requireContext(), "Code verified successfully!!", Toast.LENGTH_SHORT)
                    .show()
                if (requireArguments().getBoolean("isResetPass")){
                    findNavController().navigate(R.id.action_verifyFragment_to_newPassFragment)
                } else {
                    if (requireArguments().getBoolean("isMobile")) {
                        done()
                    } else {
                        binding.llCode.visibility = View.GONE
                        binding.llSuccessful.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    private fun done() {
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

}