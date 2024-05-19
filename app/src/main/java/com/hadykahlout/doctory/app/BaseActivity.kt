package com.hadykahlout.doctory.app

import APP_LANGUAGE
import android.content.res.Configuration
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import com.hadykahlout.doctory.utils.SharedPrefsHelper
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    private var dLocale: Locale? = null

    init {
        updateConfig(wrapper = this)
    }

    private fun updateConfig(wrapper: ContextThemeWrapper) {
        if (dLocale == Locale("")) // Do nothing if dLocale is null
            return

        if (SharedPrefsHelper.has(APP_LANGUAGE)) {

            if (SharedPrefsHelper.getString(APP_LANGUAGE, "en") == "ar") {
                dLocale = Locale("ar")
                Locale.setDefault(dLocale!!)
                val configuration = Configuration()
                configuration.setLocale(dLocale)
                wrapper.applyOverrideConfiguration(configuration)
            } else {
                dLocale = Locale("en")
                Locale.setDefault(dLocale!!)
                val configuration = Configuration()
                configuration.setLocale(dLocale)
                wrapper.applyOverrideConfiguration(configuration)
            }

        } else {
            dLocale = Locale("en")
            Locale.setDefault(dLocale!!)
            val configuration = Configuration()
            configuration.setLocale(dLocale)
            wrapper.applyOverrideConfiguration(configuration)
        }

    }

}