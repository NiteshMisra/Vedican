package com.student.extras

import android.app.Activity
import android.content.SharedPreferences

class PrefData {

    companion object {

        private const val appName : String = "STUDENT_ADMIN"

        private val preferences: SharedPreferences
            get() = INDIMaster.applicationContext().getSharedPreferences(appName, Activity.MODE_PRIVATE)

        fun preferenceEditor(): SharedPreferences.Editor {
            return INDIMaster.applicationContext().getSharedPreferences(appName, Activity.MODE_PRIVATE).edit()
        }

        fun session(value: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean("APP_SESSION", value)
            editor.commit()
        }

        fun session(): Boolean {
            val mSharedPreferences = preferences
            return mSharedPreferences.getBoolean("APP_SESSION", false)
        }

        fun phone(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("phone", "")
        }

        fun phone(value: String) {
            val editor = preferences.edit()
            editor.putString("phone", value)
            editor.commit()
        }

    }

}