package com.hadykahlout.doctory.ui.fragment.main.doctor.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentAccountBinding
import com.hadykahlout.doctory.model.api.response.auth.ServerUser
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.activity.PatientActivity
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.MainViewModel
import com.hadykahlout.doctory.utils.SharedPrefsHelper

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(layoutInflater)
        if (requireArguments().getBoolean("isDoctor")) {
            (requireActivity() as DoctorActivity).binding.navView.visibility = View.GONE
        } else {
            (requireActivity() as PatientActivity).binding.navView.visibility = View.GONE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = SharedPrefsHelper.getServerUser()

        if (user.roleId == 3){
            binding.cardEdit.visibility = View.GONE
        }

        getProfileData(user)

        binding.cardEdit.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment2_to_editAccountFragment)
        }

    }

    private fun getProfileData(user: ServerUser) {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.getProfile()
        viewModel.profileData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response

                    binding.tvUsername.text = user.username
                    binding.tvFullName.text = user.fullName
                    binding.tvBirth.text = user.dateOfBirth
                    binding.tvGender.text = user.gender
                    binding.tvPhoneNumber.text = user.mobile
                    binding.tvEmail.text = user.email


//                    binding.tvEcareID.text = ""
//                    binding.tvCity.text = ""
//                    binding.tvProvince.text = ""
//                    binding.tvAddress.text = ""

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

    override fun onDestroyView() {
        super.onDestroyView()
        if (requireArguments().getBoolean("isDoctor")) {
            (requireActivity() as DoctorActivity).binding.navView.visibility = View.VISIBLE
        } else {
            (requireActivity() as PatientActivity).binding.navView.visibility = View.VISIBLE
        }
    }

}