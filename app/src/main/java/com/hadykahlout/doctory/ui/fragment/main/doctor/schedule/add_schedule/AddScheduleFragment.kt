package com.hadykahlout.doctory.ui.fragment.main.doctor.schedule.add_schedule

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.FragmentAddScheduleBinding
import com.hadykahlout.doctory.model.api.doctor.AddSchedule
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.dialog.LoadingDialog
import com.hadykahlout.doctory.ui.fragment.main.doctor.DoctorViewModel
import com.hadykahlout.doctory.ui.sheet.SuccessBottomSheet
import com.hadykahlout.doctory.utils.DayViewContainer
import com.hadykahlout.doctory.utils.MonthViewContainer
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale

class AddScheduleFragment : Fragment() {
    private val TAG = "AddScheduleFragment"

    private lateinit var binding: FragmentAddScheduleBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[AddScheduleViewModel::class.java]
    }
    val selectedDates = ArrayList<String>()

    private val doctorViewModel by lazy {
        ViewModelProvider(this)[DoctorViewModel::class.java]
    }
    private val loading = LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddScheduleBinding.inflate(layoutInflater)
        (requireActivity() as DoctorActivity).binding.navView.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawCalender()
        observeDuration()
        setListeners()

    }

    private fun setListeners() {
        binding.imgBack.setOnClickListener {
            if (binding.llStepTwo.isVisible) {
                binding.tvSteps.text = "2/1"
                binding.llStepOne.visibility = View.VISIBLE
                binding.llStepTwo.visibility = View.GONE
            } else {
                findNavController().navigateUp()
            }
        }

        binding.btnContinue.setOnClickListener {
            if (selectedDates.isEmpty()) {
                Snackbar.make(
                    requireView(),
                    "Please choose the dates you want!!",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                binding.tvSteps.text = "2/2"
                binding.llStepOne.visibility = View.GONE
                binding.llStepTwo.visibility = View.VISIBLE
            }
        }

        binding.btnSave.setOnClickListener {
            addSchedule()
        }

        binding.cardTimeStart.setOnClickListener {
            openTimeDialog(true)
        }

        binding.cardTimeEnd.setOnClickListener {
            openTimeDialog(false)
        }

        binding.card15.setOnClickListener {
            viewModel.duration.value = 0
        }

        binding.card30.setOnClickListener {
            viewModel.duration.value = 1
        }

        binding.card45.setOnClickListener {
            viewModel.duration.value = 2
        }

        binding.card60.setOnClickListener {
            viewModel.duration.value = 3
        }
    }

    private fun observeDuration(){
        viewModel.duration.observe(viewLifecycleOwner){ status ->
            when(status){
                0 -> { // means user select 15 min
                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                1 -> { // means user select 30 min
                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                2 -> { // means user select 45 min
                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                3 -> { // means user select 60 min
                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.white))
                }
            }
        }
    }

    private fun openTimeDialog(isStart: Boolean) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { view, hourOfDay, minute ->
                if (isStart) {
                    binding.tvTimeStart.text = "$hourOfDay:$minute"
                } else {
                    binding.tvTimeEnd.text = "$hourOfDay:$minute"
                }
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }

    private fun drawCalender() {
        val currentMonth = YearMonth.now()
        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) =
                DayViewContainer(view, requireActivity()) { isSelected: Boolean, date: String ->
                    if (isSelected) {
                        Log.d(TAG, "create: select $currentMonth-$date")
                        selectedDates.add("$currentMonth-$date")
                    } else {
                        if (selectedDates.contains("$currentMonth-$date")) {
                            Log.d(TAG, "create: unselect $currentMonth-$date")
                            selectedDates.remove("$currentMonth-$date")
                        }
                    }
                }

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.textView.text = data.date.dayOfMonth.toString()
            }
        }
        val startMonth = currentMonth.minusMonths(100)  // Adjust as needed
        val endMonth = currentMonth.plusMonths(100)  // Adjust as needed
        val daysOfWeek = daysOfWeek() // Available from the library
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)
        binding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    // Remember that the header is reused so this will be called for each month.
                    // However, the first day of the week will not change so no need to bind
                    // the same view every time it is reused.
                    if (container.titlesContainer.tag == null) {
                        container.titlesContainer.tag = data.yearMonth
                        container.titlesContainer.children.map { it as TextView }
                            .forEachIndexed { index, textView ->
                                val dayOfWeek = daysOfWeek[index]
                                val title =
                                    dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                textView.text = title
                                // In the code above, we use the same `daysOfWeek` list
                                // that was created when we set up the calendar.
                                // However, we can also get the `daysOfWeek` list from the month data:
                                // val daysOfWeek = data.weekDays.first().map { it.date.dayOfWeek }
                                // Alternatively, you can get the value for this specific index:
                                // val dayOfWeek = data.weekDays.first()[index].date.dayOfWeek
                            }
                    }
                }
            }
    }

    private fun addSchedule() {
        loading.show(requireActivity().supportFragmentManager, "Loading")
        var days = ""
        selectedDates.forEach {
            if (selectedDates.indexOf(it) == (selectedDates.size - 1)){
                days = "$days$it"
            } else {
                days = "$days$it, "
            }
        }
        var duration = ""
        when(viewModel.duration.value){
            0 -> duration = "15"
            1 -> duration = "30"
            2 -> duration = "45"
            3 -> duration = "60"
        }
        val addSchedule = AddSchedule(
            days = days,
            startTime = binding.tvTimeStart.text.toString(),
            endTime = binding.tvTimeEnd.text.toString(),
            duration = duration
        )
        doctorViewModel.addSchedule(addSchedule)
        doctorViewModel.addData.observe(viewLifecycleOwner){ response ->
            loading.dismiss()
            if (response.body() != null) {
                if (response.body()!!.status && response.body()!!.code in 200..299){
                    // Here handel the request response
                    Snackbar.make(
                        requireView(),
                        response.body()!!.message,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    val successBottomSheet = SuccessBottomSheet()
                    successBottomSheet.show(requireActivity().supportFragmentManager, "Success")
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
        (requireActivity() as DoctorActivity).binding.navView.visibility = View.VISIBLE
    }

}