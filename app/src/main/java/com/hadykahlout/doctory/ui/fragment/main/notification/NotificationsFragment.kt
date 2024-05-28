package com.hadykahlout.doctory.ui.fragment.main.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.adapter.BookAppointmentAdapter
import com.hadykahlout.doctory.adapter.NotificationsAdapter
import com.hadykahlout.doctory.databinding.FragmentNotificationsBinding
import com.hadykahlout.doctory.ui.activity.MainActivity


class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationsAdapter: NotificationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).binding.navView.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        notificationsAdapter = NotificationsAdapter(requireContext())

        getData()

        binding.rcNotifications.adapter = notificationsAdapter

    }

    private fun getData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).binding.navView.visibility = View.VISIBLE
    }

}