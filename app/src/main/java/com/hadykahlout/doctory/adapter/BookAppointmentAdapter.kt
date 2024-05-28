package com.hadykahlout.doctory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hadykahlout.doctory.databinding.ItemBookAppointmentBinding
import com.hadykahlout.doctory.model.BookAppointment

class BookAppointmentAdapter(
    val context: Context,
    val onAccept: (book: BookAppointment) -> Unit,
    val onReject: (book: BookAppointment) -> Unit,
) :
    ListAdapter<BookAppointment, BookAppointmentAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BookAppointment>() {
            override fun areItemsTheSame(
                oldItem: BookAppointment,
                newItem: BookAppointment
            ): Boolean {
                return oldItem === newItem // check if the items are the same. use the id if your model has one.
            }

            override fun areContentsTheSame(
                oldItem: BookAppointment,
                newItem: BookAppointment
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemBookAppointmentBinding) :
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
            binding.tvDate.text = getItem(position).date
            binding.tvTime.text = getItem(position).time

            binding.llAccept.setOnClickListener {
                onAccept(getItem(position))
            }

            binding.llReject.setOnClickListener {
                onReject(getItem(position))
            }
        }
    }

}