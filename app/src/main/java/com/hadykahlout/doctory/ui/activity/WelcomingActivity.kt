package com.hadykahlout.doctory.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.adapter.WelcomeAdapter
import com.hadykahlout.doctory.databinding.ActivityWelcomingBinding
import com.hadykahlout.doctory.model.WelcomePage

class WelcomingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomingBinding
    private lateinit var adapter: WelcomeAdapter

    private val welcomeData = ArrayList<WelcomePage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWelcomeData()
        setIndicator(0)


        binding.cardNext.setOnClickListener {
            binding.pager.setCurrentItem((binding.pager.currentItem + 1), true)
            if (binding.pager.currentItem == (welcomeData.size - 1)) {
                binding.cardNext.visibility = View.GONE
                binding.btnStart.visibility = View.VISIBLE
            }
        }

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        binding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                setIndicator(position)
                if (position < welcomeData.size - 1) {
                    binding.cardNext.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.GONE
                } else {
                    binding.cardNext.visibility = View.GONE
                    binding.btnStart.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })

    }

    private fun getWelcomeData() {
        welcomeData.add(
            WelcomePage(
                image = R.drawable.welcome_1,
                title = "Easy Way to Get\n" +
                        "Better Life",
                hint = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat."
            )
        )
        welcomeData.add(
            WelcomePage(
                image = R.drawable.welcome_1,
                title = "Easy Way to Get\n" +
                        "Better Life",
                hint = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat."
            )
        )
        welcomeData.add(
            WelcomePage(
                image = R.drawable.welcome_1,
                title = "Easy Way to Get\n" +
                        "Better Life",
                hint = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat."
            )
        )
        adapter = WelcomeAdapter(this, welcomeData)
        binding.pager.adapter = adapter
    }

    private fun setIndicator(position: Int) {
        binding.llIndicators.removeAllViews()
        val indicators = arrayOfNulls<ImageView>(welcomeData.size)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(resources.getDrawable(R.drawable.unviewed_index, resources.newTheme()))
            indicators[i]!!.layoutParams = layoutParams
            binding.llIndicators.addView(indicators[i])
        }
        indicators[position]!!.setImageDrawable(resources.getDrawable(R.drawable.viewed_index, resources.newTheme()))
    }

}