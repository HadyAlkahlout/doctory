package com.hadykahlout.doctory.adapter.doctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hadykahlout.doctory.databinding.ItemMonthAppointmentBinding
import com.hadykahlout.doctory.model.MonthAppointment

class MonthAppointmentsAdapter(
    val context: Context,
    val onAccept: (id: String) -> Unit,
    val onReject: (id: String) -> Unit,
) :
    ListAdapter<MonthAppointment, MonthAppointmentsAdapter.ViewHolder>(diffCallback) {

    private lateinit var adapter: AppointmentAdapter
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MonthAppointment>() {
            override fun areItemsTheSame(
                oldItem: MonthAppointment,
                newItem: MonthAppointment
            ): Boolean {
                return oldItem === newItem // check if the items are the same. use the id if your model has one.
            }

            override fun areContentsTheSame(
                oldItem: MonthAppointment,
                newItem: MonthAppointment
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMonthAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemMonthAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            // bind the view
            binding.tvMonth.text = getItem(position).month

            adapter = AppointmentAdapter(
                context,
                { id ->
                    onAccept(id)
                },
                { id ->
                    onReject(id)
                })
            adapter.submitList(getItem(position).appointments)
            binding.rcSchedule.adapter = adapter

        }
    }

}