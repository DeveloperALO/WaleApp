package com.aur3liux.waleapp

import android.app.Application
import android.content.Context

class AppWale: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}