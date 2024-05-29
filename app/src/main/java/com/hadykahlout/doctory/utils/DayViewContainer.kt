package com.hadykahlout.doctory.utils

import android.app.Activity
import android.view.View
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.ItemCalendarDayBinding
import com.hadykahlout.doctory.ui.activity.PatientActivity
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View, activity: Activity, onSelect: (isSelected: Boolean, date: String) -> Unit) : ViewContainer(view) {

     val textView = ItemCalendarDayBinding.bind(view).calendarDayText
     val cardText = ItemCalendarDayBinding.bind(view).cardDate
     var isSelected = false

     init {
         textView.setOnClickListener {
              isSelected = !isSelected
              onSelect(isSelected, textView.text.trim().toString())
              if (isSelected){
                   textView.setTextColor(activity.getColor(R.color.white))
                   cardText.setCardBackgroundColor(activity.getColor(R.color.colorPrimary))
              } else {
                   textView.setTextColor(activity.getColor(R.color.black))
                   cardText.setCardBackgroundColor(activity.getColor(android.R.color.transparent))
              }
         }
     }
}