package com.student.extras

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.GsonBuilder

class INDIMaster : Application(){

    var context: Context? = null


    override fun onCreate() {
        super.onCreate()
        instance = this@INDIMaster
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = this.baseContext

    }

    companion object {

        @get:Synchronized
        var instance: INDIMaster? = null
            private set

        fun applicationContext() : INDIMaster {
            return instance!!.applicationContext as INDIMaster
        }

        private val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

    }
}