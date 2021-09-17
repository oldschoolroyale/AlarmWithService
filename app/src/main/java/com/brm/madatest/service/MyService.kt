package com.brm.madatest.service

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.brm.madatest.data.room.Location
import com.brm.madatest.data.room.LocationRepo
import com.brm.madatest.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.tasks.OnSuccessListener

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.CountDownTimer
import android.os.Environment
import android.util.Log

import androidx.core.app.ActivityCompat
import com.brm.madatest.utils.AudioRecorder

import com.google.android.gms.location.LocationServices

import com.google.android.gms.location.FusedLocationProviderClient
import java.io.IOException
import javax.inject.Named


@AndroidEntryPoint
class MyService @Inject constructor() : BroadcastReceiver(), CoroutineScope {

    @Inject
    lateinit var repo: LocationRepo

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job




    override fun onReceive(context: Context?, intent: Intent?) {
//        startRecording()
    }



    private fun cleanUp(){
        AppPreferences.location = 0
        launch {
            repo.deleteAll()
        }
    }
}