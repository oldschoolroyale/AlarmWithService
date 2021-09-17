package com.brm.madatest.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brm.madatest.data.room.Location
import com.brm.madatest.data.room.LocationRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repository: LocationRepo) : ViewModel() {

    val getAllData: LiveData<List<Location>> = repository.getAllData

    fun insertData(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(location)
        }
    }
}