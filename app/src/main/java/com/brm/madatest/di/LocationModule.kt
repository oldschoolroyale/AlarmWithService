package com.brm.madatest.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.room.Room
import com.brm.madatest.data.room.Location
import com.brm.madatest.data.room.LocationRoom
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocationModule {

    @Singleton
    @Provides
    @Named("location_room")
    fun provideRoom(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            LocationRoom::class.java,
            "airsoft_db"
        ).build()

    @Singleton
    @Provides
    @Named("location_dao")
    fun provideDao(@Named("location_room") db: LocationRoom) = db.getLocationDao()

    companion object{
        private const val GOOGLE_MAPS_URL = "https://maps.google.com/?q="
        private val formatter = SimpleDateFormat("dd:MM:yyyy", Locale.getDefault())
    }
}