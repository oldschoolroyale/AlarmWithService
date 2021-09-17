package com.brm.madatest.data.room

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Named

class LocationRepo
@Inject
constructor(@Named("location_dao")
            private val locationDao: LocationDao) {

    val getAllData: LiveData<List<Location>> = locationDao.getAllData()

    suspend fun insertData(location: Location){
        locationDao.insertData(location)
    }

    suspend fun deleteAll(){
        locationDao.deleteAll()
    }
}