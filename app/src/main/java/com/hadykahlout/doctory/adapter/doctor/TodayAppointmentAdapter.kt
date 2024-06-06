package com.hadykahlout.doctory.adapter.doctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hadykahlout.doctory.databinding.ItemTodayAppointmentBinding
import com.hadykahlout.doctory.model.Appointment

class TodayAppointmentAdapter(val context: Context) :
    ListAdapter<Appointment, TodayAppointmentAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Appointment>() {
            override fun areItemsTheSame(
                oldItem: Appointment,
                newItem: Appointment
            ): Boolean {
                return oldItem === newItem // check if the items are the same. use the id if your model has one.
            }

            override fun areContentsTheSame(
                oldItem: Appointment,
                newItem: Appointment
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTodayAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemTodayAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.tvFrom.text = getItem(position).timeFrom
            binding.tvTo.text = getItem(position).timeTo
            binding.tvName.text = getItem(position).name
            binding.tvCategory.text = getItem(position).category

            if (getItem(position).image != ""){
                Glide
                    .with(context)
                    .load(getItem(position).image)
                    .into(binding.imgPatient)
            }
        }
    }

}