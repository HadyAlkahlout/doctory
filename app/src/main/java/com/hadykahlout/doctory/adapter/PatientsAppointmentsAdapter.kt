package com.hadykahlout.doctory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hadykahlout.doctory.databinding.ItemPatientAppointmentBinding
import com.hadykahlout.doctory.model.PatientAppointment

class PatientsAppointmentsAdapter(val context: Context, val onMessage: (patient: PatientAppointment) -> Unit) :
    ListAdapter<PatientAppointment, PatientsAppointmentsAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PatientAppointment>() {
            override fun areItemsTheSame(
                oldItem: PatientAppointment,
                newItem: PatientAppointment
            ): Boolean {
                return oldItem === newItem // check if the items are the same. use the id if your model has one.
            }

            override fun areContentsTheSame(
                oldItem: PatientAppointment,
                newItem: PatientAppointment
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPatientAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemPatientAppointmentBinding) :
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

            binding.imgMessage.setOnClickListener {
                onMessage(getItem(position))
            }
        }
    }

}