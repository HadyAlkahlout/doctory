package com.hadykahlout.doctory.ui.fragment.main.doctor.profile.education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {

    private lateinit var binding: FragmentEducationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}