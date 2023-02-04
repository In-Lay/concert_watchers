package com.inlay.concertswatcher.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class SearchViewModel : ViewModel() {
    abstract val body: MutableLiveData<String>
    abstract val name: MutableLiveData<String>
    abstract val minDate: MutableLiveData<Long>
    abstract val maxDate: MutableLiveData<Long>

    abstract fun addBody(body: String)
    abstract fun addName(name: String)
    abstract fun addMinDate(minDate: Long)
    abstract fun addMaxDate(maxDate: Long)
}