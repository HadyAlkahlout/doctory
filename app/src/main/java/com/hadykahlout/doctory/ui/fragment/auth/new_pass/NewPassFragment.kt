package com.hadykahlout.doctory.ui.fragment.auth.new_pass

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentNewPassBinding
import com.hadykahlout.doctory.ui.activity.AuthActivity

class NewPassFragment : Fragment() {

    private lateinit var binding: FragmentNewPassBinding

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
            binding.passwordLayout.error = "Required!!"
        } else if (binding.etConfirm.text!!.isEmpty()){
            binding.passwordLayout.error = null
            binding.confirmLayout.error = "Required!!"
        } else if (binding.etConfirm.text!!.trim().toString() != binding.etPassword.text!!.trim().toString()){
            Log.e("TAG", "checkPass: Pass ${binding.etConfirm.text!!} != ${binding.etPassword.text!!}", )
            binding.confirmLayout.error = "Confirm password do not match the new password!!"
        } else {
            binding.passwordLayout.error = null
            binding.confirmLayout.error = null
            binding.llPassword.visibility = View.GONE
            binding.llSuccessful.visibility = View.VISIBLE
        }
    }

}
