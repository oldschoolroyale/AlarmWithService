package com.brm.madatest.ui

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

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myViewModel by viewModels<MainViewModel>()

    private val locationObserver = Observer<List<Location>>{
        if (it.isNotEmpty())
            binding.mainTxt.text = it[0].time.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerReceiver()

        myViewModel.getAllData.observe(this, locationObserver)

    }

    private fun registerReceiver(){
        val timeInMillis = System.currentTimeMillis() + 5000
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyService::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }
}