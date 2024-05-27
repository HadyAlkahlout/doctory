package com.hadykahlout.doctory.adapter

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.almnaber.nahidh.databinding.ItemNotificationBinding
//import com.almnaber.nahidh.model.main.notification.Notification

//class NotificationsAdapter :
//    ListAdapter<Notification, NotificationsAdapter.ViewHolder>(diffCallback) {
//
//    var isLoading = false
//
//    // DiffUtil
//    companion object {
//        private val diffCallback = object : DiffUtil.ItemCallback<Notification>() {
//            override fun areItemsTheSame(
//                oldItem: Notification,
//                newItem: Notification
//            ): Boolean {
//                return oldItem === newItem // check if the items are the same. use the id if your model has one.
//            }
//
//            override fun areContentsTheSame(
//                oldItem: Notification,
//                newItem: Notification
//            ): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(position)
//        if (position == itemCount - 1 && !isLoading) {
//            onItemListener.onPaginate()
//        }
//    }
//
//    inner class ViewHolder(private val binding: ItemNotificationBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            binding.imgDelete.setOnClickListener {
//                onItemListener.onDeleteItem(
//                    getItem(adapterPosition).notyMessagesId!!,
//                    getItem(adapterPosition),
//                    adapterPosition
//                )
//            }
//        }
//
//        fun bind(position: Int) {
//            // bind the view
//            //binding.model.text = getItem(position).text
//            binding.tvTitle.text = getItem(position).notyMessagesTitle
//            binding.tvDetails.text = getItem(position).notyMessagesBody
//            binding.tvDate.text = getItem(position).notyMessagesDateTime
//
//            binding.root.setOnClickListener {
//                onItemListener.onClick(getItem(position))
//            }
//        }
//    }
//
//    private lateinit var onItemListener: OnItemListener
//
//    interface OnItemListener {
//        fun onDeleteItem(id: String, currentItem: Notification, position: Int)
//        fun onPaginate()
//        fun onClick(item: Notification)
//
//    }
//
//    fun setListeners(onItemListener: OnItemListener) {
//        this.onItemListener = onItemListener
//    }
//
//}