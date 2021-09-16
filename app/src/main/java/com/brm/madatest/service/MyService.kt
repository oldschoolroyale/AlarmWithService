package com.brm.madatest.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.app.Activity
import android.view.View
import com.brm.madatest.data.room.Location
import com.brm.madatest.ui.MainViewModel
import com.brm.madatest.utils.AppPreferences
import java.lang.Exception
import javax.inject.Inject



class MyService: BroadcastReceiver() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onReceive(context: Context?, intent: Intent?) {
        mainViewModel.insertData(Location(
            0,
            1204102401240,
            55.04444,
            40.000000
        ))
    }
}