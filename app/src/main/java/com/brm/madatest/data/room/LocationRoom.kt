package com.brm.madatest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class LocationRoom : RoomDatabase() {

    abstract fun getLocationDao(): LocationDao
}