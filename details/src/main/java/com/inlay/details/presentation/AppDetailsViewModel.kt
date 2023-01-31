package com.inlay.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inlay.details.data.Data

class AppDetailsViewModel : DetailsViewModel() {
    private val _concertsDetails = MutableLiveData<Data>()
    override val concertsDetails: LiveData<Data> = _concertsDetails

    override fun assignDetailsData(detailsData: Data) {
        _concertsDetails.postValue(detailsData)
    }
}