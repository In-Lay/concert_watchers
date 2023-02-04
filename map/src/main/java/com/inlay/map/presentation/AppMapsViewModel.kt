package com.inlay.map.presentation

import androidx.lifecycle.MutableLiveData
import com.inlay.map.data.Geo

class AppMapsViewModel : MapsViewModel() {
    private val _mapsGeo = MutableLiveData<Geo>()
    override val mapsGeo: MutableLiveData<Geo> = _mapsGeo

    override fun addGeo(geo: Geo) {
        _mapsGeo.value = geo
    }
}