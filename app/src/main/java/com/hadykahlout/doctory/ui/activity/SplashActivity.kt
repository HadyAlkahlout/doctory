package com.hadykahlout.doctory.ui.activity

import IS_FIRST_TIME
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.app.BaseActivity
import com.hadykahlout.doctory.databinding.ActivitySplashBinding
import com.hadykahlout.doctory.ui.dialog.InternetDialog
import com.hadykahlout.doctory.utils.HelperFunctions
import com.hadykahlout.doctory.utils.SharedPrefsHelper

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({

            if (HelperFunctions.checkForInternet()){
                if (SharedPrefsHelper.has(IS_FIRST_TIME)){
                    startActivity(Intent(this.baseContext, MainActivity::class.java))
                } else {
                    startActivity(Intent(this.baseContext, MainActivity::class.java))
//                    startActivity(Intent(this.baseContext, WelcomingActivity::class.java))
                }
                finish()
            }else{
                InternetDialog().show(supportFragmentManager, null)
            }

        }, 4000)

    }
}