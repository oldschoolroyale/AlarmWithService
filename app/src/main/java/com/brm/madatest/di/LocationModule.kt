package com.brm.madatest.di

import android.content.Context
import androidx.room.Room
import com.brm.madatest.data.room.LocationDao
import com.brm.madatest.data.room.LocationRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class LocationModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext appContext: Context): LocationRoom{
        return Room.databaseBuilder(
            appContext,
            LocationRoom::class.java,
            ""
        ).build()
    }
}