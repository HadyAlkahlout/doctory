package com.hadykahlout.doctory.ui.fragment.main.doctor.appointment.add_schedule

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
import com.hadykahlout.doctory.ui.activity.DoctorActivity
import com.hadykahlout.doctory.ui.sheet.SuccessBottomSheet
import com.hadykahlout.doctory.utils.DayViewContainer
import com.hadykahlout.doctory.utils.MonthViewContainer
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import java.time.Year
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
        observeTimeStatus()
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
            val successBottomSheet = SuccessBottomSheet()
            successBottomSheet.show(requireActivity().supportFragmentManager, "Success")
        }

        binding.cardTimeStart.setOnClickListener {
            openTimeDialog(true)
        }

        binding.cardTimeEnd.setOnClickListener {
            openTimeDialog(false)
        }

        binding.cardAMStart.setOnClickListener {
            changeTimeStatus(true, 0)
        }

        binding.cardPMStart.setOnClickListener {
            changeTimeStatus(true, 1)
        }

        binding.cardAMEnd.setOnClickListener {
            changeTimeStatus(false, 0)
        }

        binding.cardPMEnd.setOnClickListener {
            changeTimeStatus(false, 1)
        }

        binding.card5.setOnClickListener {
            viewModel.duration.value = 0
        }

        binding.card15.setOnClickListener {
            viewModel.duration.value = 1
        }

        binding.card30.setOnClickListener {
            viewModel.duration.value = 2
        }

        binding.card45.setOnClickListener {
            viewModel.duration.value = 3
        }

        binding.card60.setOnClickListener {
            viewModel.duration.value = 4
        }
    }

    private fun observeDuration(){
        viewModel.duration.observe(viewLifecycleOwner){ status ->
            when(status){
                0 -> {  // means user select 15 min
                    binding.card5.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv5.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                1 -> { // means user select 15 min
                    binding.card5.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv5.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                2 -> { // means user select 30 min
                    binding.card5.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv5.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                3 -> { // means user select 45 min
                    binding.card5.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv5.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card15.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv15.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card30.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv30.setTextColor(requireActivity().getColor(R.color.black))

                    binding.card45.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                    binding.tv45.setTextColor(requireActivity().getColor(R.color.white))

                    binding.card60.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv60.setTextColor(requireActivity().getColor(R.color.black))
                }
                4 -> { // means user select 60 min
                    binding.card5.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                    binding.tv5.setTextColor(requireActivity().getColor(R.color.black))

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

    private fun changeTimeStatus(isStart: Boolean, status: Int) {
        if (isStart){
            viewModel.startTimeStatus.value = status
        } else {
            viewModel.endTimeStatus.value = status
        }
    }

    private fun observeTimeStatus() {
        viewModel.startTimeStatus.observe(viewLifecycleOwner){ status ->
            if (status == 0){
                binding.cardAMStart.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                binding.tvAMStart.setTextColor(requireActivity().getColor(R.color.white))
                binding.cardPMStart.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                binding.tvPMStart.setTextColor(requireActivity().getColor(R.color.black))
            } else {
                binding.cardAMStart.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                binding.tvAMStart.setTextColor(requireActivity().getColor(R.color.black))
                binding.cardPMStart.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                binding.tvPMStart.setTextColor(requireActivity().getColor(R.color.white))
            }
        }

        viewModel.endTimeStatus.observe(viewLifecycleOwner){ status ->
            if (status == 0){
                binding.cardAMEnd.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                binding.tvAMEnd.setTextColor(requireActivity().getColor(R.color.white))
                binding.cardPMEnd.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                binding.tvPMEnd.setTextColor(requireActivity().getColor(R.color.black))
            } else {
                binding.cardAMEnd.setCardBackgroundColor(requireActivity().getColor(R.color.softGray))
                binding.tvAMEnd.setTextColor(requireActivity().getColor(R.color.black))
                binding.cardPMEnd.setCardBackgroundColor(requireActivity().getColor(R.color.colorPrimary))
                binding.tvPMEnd.setTextColor(requireActivity().getColor(R.color.white))
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
        val currentYear = Year.now()
        val currentMonth = YearMonth.now()
        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) =
                DayViewContainer(view, requireActivity()) { isSelected: Boolean, date: String ->
                    if (isSelected) {
                        Log.d(TAG, "create: select $date/$currentMonth/$currentYear")
                        selectedDates.add("$date/$currentMonth/$currentYear")
                    } else {
                        if (selectedDates.contains("$date/$currentMonth/$currentYear")) {
                            Log.d(TAG, "create: unselect $date/$currentMonth/$currentYear")
                            selectedDates.remove("$date/$currentMonth/$currentYear")
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

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as DoctorActivity).binding.navView.visibility = View.VISIBLE
    }

}