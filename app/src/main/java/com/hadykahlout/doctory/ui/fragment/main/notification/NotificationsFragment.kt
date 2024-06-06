package com.hadykahlout.doctory.ui.fragment.main.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.adapter.NotificationsAdapter
import com.hadykahlout.doctory.databinding.FragmentNotificationsBinding
import com.hadykahlout.doctory.model.Notification
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.activity.PatientActivity
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.MainViewModel


class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationsAdapter: NotificationsAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val loading = LoadingDialog()
    private val notifications = ArrayList<Notification>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        if(requireArguments().getBoolean("isDoctor")) {
            (requireActivity() as DoctorActivity).binding.navView.visibility = View.GONE
        } else {
            (requireActivity() as PatientActivity).binding.navView.visibility = View.GONE
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        notificationsAdapter = NotificationsAdapter(requireContext())
        notificationsAdapter.submitList(notifications)

        getData()

        binding.rcNotifications.adapter = notificationsAdapter

    }

    private fun getData() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        viewModel.getNotification()
        viewModel.notificationData.observe(viewLifecycleOwner) { response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299) {
                    // Here handel the request response

                    notifications.add(
                        Notification(
                            0,
                            "",
                            "New Message from Rakabuming",
                            "Lorem ipsum dolor sit amet cosectures adipsing chalengge",
                            "Thu Jun 21",
                            "8:00 AM"
                        )
                    )
                    notifications.add(
                        Notification(
                            1,
                            "",
                            "New Message from Rakabuming",
                            "Lorem ipsum dolor sit amet cosectures adipsing chalengge",
                            "Thu Jun 21",
                            "8:00 AM"
                        )
                    )

                    notificationsAdapter.notifyDataSetChanged()

                } else {
                    Snackbar.make(
                        requireView(),
                        response.body()!!.message, Snackbar.LENGTH_SHORT
                    ).show()
                    binding.llData.visibility = View.GONE
                    binding.llEmptyData.visibility = View.VISIBLE
                }
            } else {
                Snackbar.make(
                    requireView(),
                    getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT
                ).show()
                binding.llData.visibility = View.GONE
                binding.llEmptyData.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(requireArguments().getBoolean("isDoctor")) {
            (requireActivity() as DoctorActivity).binding.navView.visibility = View.VISIBLE
        } else {
            (requireActivity() as PatientActivity).binding.navView.visibility = View.VISIBLE
        }
    }

}