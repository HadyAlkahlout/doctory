package com.hadykahlout.doctory.ui.fragment.main.doctor.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentEditAccountBinding

class EditAccountFragment : Fragment() {

    private lateinit var binding: FragmentEditAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}