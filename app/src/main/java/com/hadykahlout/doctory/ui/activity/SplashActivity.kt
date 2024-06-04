package com.hadykahlout.doctory.ui.activity

import IS_FIRST_OPEN
import SERVER_USER
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.hadykahlout.doctory.R
import com.hadykahlout.doctory.app.BaseActivity
import com.hadykahlout.doctory.databinding.ActivitySplashBinding
import com.hadykahlout.doctory.model.api.response.auth.ServerUser
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
                if (SharedPrefsHelper.has(IS_FIRST_OPEN)){
                    if (SharedPrefsHelper.has(SERVER_USER)){

                        val serverUser = SharedPrefsHelper.getServerUser()
                        if (serverUser.roleId == 2){
                            startActivity(Intent(this.baseContext, DoctorActivity::class.java))
                        } else if (serverUser.roleId == 3){
                            startActivity(Intent(this.baseContext, PatientActivity::class.java))
                        } else if (serverUser.roleId == 1){
                            Toast.makeText(this,
                                getString(R.string.please_open_your_account_using_the_dashboard), Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        startActivity(Intent(this.baseContext, AuthActivity::class.java))
                    }
                } else {
                    startActivity(Intent(this.baseContext, WelcomingActivity::class.java))
                }
                finish()
            }else{
                InternetDialog().show(supportFragmentManager, null)
            }

        }, 4000)

    }
}

