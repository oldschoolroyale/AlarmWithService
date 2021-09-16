package com.brm.madatest

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.brm.madatest.utils.AppPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.setUp(this)
    }
}