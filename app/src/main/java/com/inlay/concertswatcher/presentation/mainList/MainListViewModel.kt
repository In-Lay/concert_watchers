package com.inlay.concertswatcher.presentation.mainList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.concertswatcher.data.ConcertsData

abstract class MainListViewModel : ViewModel() {
    abstract val concertsData: LiveData<ConcertsData>
    abstract val error: LiveData<String>
    abstract val isLoading: LiveData<Boolean>


    abstract fun getConcerts(body: String, name: String, page: Int)

    abstract fun addTempConcerts(concertsData: ConcertsData)
}