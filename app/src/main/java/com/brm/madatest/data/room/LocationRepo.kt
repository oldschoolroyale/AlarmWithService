package com.brm.madatest.data.room

import androidx.lifecycle.LiveData

class LocationRepo(private val locationDao: LocationDao) {
    val getAllData: LiveData<List<Location>> = locationDao.getAllData()

    suspend fun insertData(location: Location){
        locationDao.insertData(location)
    }
}