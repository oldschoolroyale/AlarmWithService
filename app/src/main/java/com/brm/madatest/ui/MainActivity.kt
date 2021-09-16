package com.brm.madatest.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.brm.madatest.databinding.ActivityMainBinding
import com.brm.madatest.service.MyService
import com.brm.madatest.utils.AppPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerReceiver()

    }

    private fun registerReceiver(){
        val timeInMillis = System.currentTimeMillis() + 5000
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyService::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }
}