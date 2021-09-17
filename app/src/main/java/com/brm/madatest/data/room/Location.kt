package com.brm.madatest.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location_table")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: String,
    val type: String,
    val locationUrl: String
)
