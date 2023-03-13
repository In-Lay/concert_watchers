package com.inlay.map.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inlay.map.data.MapGeoModel

abstract class MapsViewModel : ViewModel() {
    abstract val mapsMapGeoModel: MutableLiveData<MapGeoModel?>

    abstract val onMapsFocused: MutableLiveData<Boolean>

    abstract fun setMapsFocus(isFocused: Boolean)

    abstract fun addGeo(mapGeoModel: MapGeoModel?)
}