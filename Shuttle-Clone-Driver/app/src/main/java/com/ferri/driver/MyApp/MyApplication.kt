package com.ferri.driver.MyApp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.ferri.driver.MyApp.MyApplication

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this
        setAppContext(applicationContext)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setAppContext(mAppContext: Context) {
        appContext = mAppContext
    }

    companion object {
        var appContext: Context? = null
        var instance: MyApplication? = null
            private set
    }
}