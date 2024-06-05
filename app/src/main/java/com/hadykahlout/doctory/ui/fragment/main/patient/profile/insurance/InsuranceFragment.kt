package com.hadykahlout.doctory.ui.fragment.main.patient.profile.insurance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentInsuranceBinding
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.activity.PatientActivity

class InsuranceFragment : Fragment() {

    private lateinit var binding: FragmentInsuranceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsuranceBinding.inflate(layoutInflater)
        (requireActivity() as PatientActivity).binding.navView.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as PatientActivity).binding.navView.visibility = View.VISIBLE
    }

}