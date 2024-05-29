package com.hadykahlout.doctory.ui.sheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hadykahlout.doctory.databinding.BottomSheetDoneBinding
import com.hadykahlout.doctory.ui.activity.DoctorActivity

class SuccessBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDoneBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHome.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), DoctorActivity::class.java))
            requireActivity().finish()
        }

    }

}