package com.brm.madatest.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(location: Location)

    @Query("SELECT * FROM location_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Location>>

    @Query("DELETE FROM location_table")
    suspend fun deleteAll()
}