package com.inlay.concertswatcher.presentation.mainList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inlay.concertswatcher.data.ConcertsData
import com.inlay.concertswatcher.domain.mainList.GetConcerts
import kotlinx.coroutines.launch

class AppMainListViewModel(private val getConcerts: GetConcerts) : MainListViewModel() {
    private val _error = MutableLiveData<String>()
    override val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private val _concertsData = MutableLiveData<ConcertsData>()
    override val concertsData: LiveData<ConcertsData> = _concertsData

    //TODO Possible problems with ViewModel
    init {
        Log.d("MainListFragmentLog", "MainListViewModel init")
    }

    override fun getConcerts(body: String, name: String, page: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val concerts = getConcerts.getConcerts(body, name, page)
            _isLoading.postValue(false)
            if (concerts.isSuccessful) {
                _concertsData.postValue(concerts.body())
            } else {
                _error.postValue(concerts.message())
            }
        }
    }
}