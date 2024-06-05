package com.hadykahlout.doctory.ui.fragment.main.doctor.profile.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentLegalBinding

class LegalFragment : Fragment() {

    private lateinit var binding: FragmentLegalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLegalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}