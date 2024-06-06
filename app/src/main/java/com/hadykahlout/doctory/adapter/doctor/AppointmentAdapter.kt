package com.hadykahlout.doctory.adapter.doctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hadykahlout.doctory.databinding.ItemAppointmentBinding
import com.hadykahlout.doctory.model.Appointment

class AppointmentAdapter(
    val context: Context,
    val onAccept: (id: String) -> Unit,
    val onReject: (id: String) -> Unit,
) :
    ListAdapter<Appointment, AppointmentAdapter.ViewHolder>(diffCallback) {

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
            ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            // bind the view
            if (getItem(position).image != null && getItem(position).image != "") {
                Glide
                    .with(context)
                    .load(getItem(position).image)
                    .into(binding.imgPatient)
            }
            binding.tvName.text = getItem(position).name
            binding.tvCategory.text = getItem(position).category
            binding.tvDayName.text = getItem(position).day
            binding.tvDayNum.text = getItem(position).date
            binding.tvTime.text = getItem(position).time

            binding.llAccept.setOnClickListener {
                onAccept(getItem(position).id.toString())
            }

            binding.llReject.setOnClickListener {
                onReject(getItem(position).id.toString())
            }
        }
    }

}