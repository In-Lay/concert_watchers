package com.inlay.map.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inlay.map.data.MapGeoModel

abstract class MapsViewModel : ViewModel() {
    abstract val mapsMapGeoModel: MutableLiveData<MapGeoModel?>

    abstract fun addGeo(mapGeoModel: MapGeoModel?)
}