package com.brm.madatest.ui

import android.Manifest.permission
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.brm.madatest.data.room.Location
import com.brm.madatest.databinding.ActivityMainBinding
import com.brm.madatest.service.MyService
import com.brm.madatest.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.R

import android.media.MediaPlayer

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE

import android.Manifest.permission.RECORD_AUDIO

import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat

import android.media.MediaRecorder

import android.os.Environment
import java.io.IOException


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myViewModel: MainViewModel by viewModels()

    private var mRecorder: MediaRecorder? = null
    private var mFileName: String? = null
    val REQUEST_AUDIO_PERMISSION_CODE = 1

    private val locationObserver = Observer<List<Location>>{
//        if (it.isNotEmpty())
//            for (i in it){
//                Log.d("shox", it.size.toString())
//                val oldText = binding.mainTxt.text
//                val text = "${i.type} ${i.locationUrl} ${i.time}"
//                binding.mainTxt.text = "$oldText\n$text"
//            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.start.setOnClickListener {
            startRecording()
        }
        binding.stop.setOnClickListener {
            pauseRecording()
        }
        myViewModel.getAllData.observe(this, locationObserver)

    }

    private fun registerReceiver(){
        val timeInMillis = System.currentTimeMillis() + 5000
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyService::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    private fun startRecording() {
        if (checkPermissions()) {
            mFileName = Environment.getExternalStorageDirectory().absolutePath
            mFileName += "/AudioRecording.3gp"
            mRecorder = MediaRecorder()
            mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
            mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mRecorder!!.setOutputFile(mFileName)
            try {
                mRecorder!!.prepare()
            } catch (e: IOException) {
                Log.e("TAG", "prepare() failed")
            }
            mRecorder!!.start()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_AUDIO_PERMISSION_CODE -> if (grantResults.size > 0) {
                val permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (permissionToRecord && permissionToStore) {
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkPermissions(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, RECORD_AUDIO)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(RECORD_AUDIO, WRITE_EXTERNAL_STORAGE),
            REQUEST_AUDIO_PERMISSION_CODE
        )
    }


    private fun pauseRecording() {
        mRecorder?.stop()
        mRecorder?.release()
        mRecorder = null
    }


}