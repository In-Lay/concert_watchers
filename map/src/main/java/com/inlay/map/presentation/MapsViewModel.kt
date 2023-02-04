package com.inlay.map.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inlay.map.data.Geo

abstract class MapsViewModel : ViewModel() {
    abstract val mapsGeo: MutableLiveData<Geo>

    abstract fun addGeo(geo: Geo)
}