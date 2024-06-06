package com.hadykahlout.doctory.ui.fragment.main.doctor.profile.account

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentEditAccountBinding
import com.hadykahlout.doctory.model.api.doctor.UpdateProfile
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditAccountFragment : Fragment() {

    private lateinit var binding: FragmentEditAccountBinding
    private val calendar = Calendar.getInstance()

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.etBirth.setOnClickListener {
            showDatePicker()
        }

        binding.btnSave.setOnClickListener {
            checkSave()
        }

    }

    private fun checkSave() {
        if (
            binding.etUsername.text!!.isEmpty()
            || binding.etFullName.text!!.isEmpty()
            || binding.etBirth.text!!.isEmpty()
            || binding.etPhone.text!!.isEmpty()
            || binding.etEmail.text!!.isEmpty()
            || binding.etCity.text!!.isEmpty()
            || binding.etProvince.text!!.isEmpty()
            || binding.etAddress.text!!.isEmpty()
        ){
            Snackbar.make(requireView(),
                getString(R.string.please_fill_the_empty_fields), Snackbar.LENGTH_SHORT).show()
        } else {
            editAccount()
        }
    }

    private fun editAccount() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        val updateProfile = UpdateProfile(
            username = binding.etUsername.text.toString(),
            fullName = binding.etFullName.text.toString(),
            birthDate = binding.etBirth.text.toString(),
            gender = if (binding.rgGender.checkedRadioButtonId == binding.rbMale.id) "male" else "female",
            mobile = "${binding.countryCode.selectedCountryCode}${binding.etPhone.text}",
            email = binding.etEmail.text.toString(),
            city = binding.etCity.text.toString(),
            province = binding.etProvince.text.toString(),
            address = binding.etAddress.text.toString(),
        )

        viewModel.updateProfile(updateProfile)
        viewModel.updateProfileData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response

                    Snackbar.make(
                        requireView(),
                        response.body()!!.message, Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()

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

    private fun showDatePicker() {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(), {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                binding.etBirth.setText("$formattedDate")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

}