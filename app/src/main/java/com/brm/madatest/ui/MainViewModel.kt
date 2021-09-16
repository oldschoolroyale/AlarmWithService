package com.brm.madatest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brm.madatest.data.room.Location
import com.brm.madatest.data.room.LocationRepo
import com.brm.madatest.data.room.LocationRoom
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ViewModelScoped
class MainViewModel(locationRoom: LocationRoom) : ViewModel() {

    private val locationDao = locationRoom.getLocationDao()
    private val repository = LocationRepo(locationDao)
    val getAllData: LiveData<List<Location>> = repository.getAllData


    fun insertData(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(location)
        }
    }
}