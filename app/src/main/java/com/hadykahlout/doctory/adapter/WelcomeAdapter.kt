package com.hadykahlout.doctory.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.databinding.PageWelcomeBinding
import com.hadykahlout.doctory.model.WelcomePage

class WelcomeAdapter(val activity: Activity, val data: ArrayList<WelcomePage>) : PagerAdapter() {

    private lateinit var binding: PageWelcomeBinding
    private lateinit var layoutInflater: LayoutInflater

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(activity)
        layoutInflater.inflate(R.layout.page_welcome, container, false)
        binding = PageWelcomeBinding.inflate(layoutInflater)

        binding.imgWelcome.setImageDrawable(
            activity.getDrawable(data[position].image)
        )
        binding.tvTitle.text = data[position].title
        binding.tvHint.text = data[position].hint

        container.addView(binding.root)
        return  binding.root
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }


}