package com.hadykahlout.doctory.utils

import android.content.Context
import android.content.SharedPreferences
import com.hadykahlout.doctory.app.App

object SharedPrefsHelper {


    private var sharedPreferences: SharedPreferences =
        App.getInstance().getSharedPreferences("shared_doc", Context.MODE_PRIVATE)

    fun delete(key: String) {
        if (sharedPreferences.contains(key)) {
            sharedPreferences.edit().remove(key).apply()
        }
    }

    fun clearAll(){
        sharedPreferences.edit().clear().apply()
    }

    fun save(key: String, value: Any?) {
        val editor = sharedPreferences.edit()
        when {
            value is Boolean -> editor.putBoolean(key, (value))
            value is Int -> editor.putInt(key, (value))
            value is Float -> editor.putFloat(key, (value))
            value is Long -> editor.putLong(key, (value))
            value is String -> editor.putString(key, value)
            value is Enum<*> -> editor.putString(key, value.toString())
            value != null -> throw RuntimeException("Attempting to save non-supported preference")
        }
        editor.apply()
    }

    fun has(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun getString(key: String, defult: String): String {
        return sharedPreferences.getString(key, defult)!!
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String): T {
        return sharedPreferences.all[key] as T
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T
        return returnValue ?: defValue
    }


}