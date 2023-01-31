package com.inlay.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.details.data.Data

abstract class DetailsViewModel() : ViewModel() {
    abstract val concertsDetails: LiveData<Data>

    abstract fun assignDetailsData(detailsData: Data)
}