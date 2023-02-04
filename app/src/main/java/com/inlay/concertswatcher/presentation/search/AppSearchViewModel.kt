package com.inlay.concertswatcher.presentation.search

import androidx.lifecycle.MutableLiveData

class AppSearchViewModel : SearchViewModel() {
    private val _body = MutableLiveData<String>()
    override val body: MutableLiveData<String> = _body

    private val _name = MutableLiveData<String>()
    override val name: MutableLiveData<String> = _name

    private val _minDate = MutableLiveData<Long>()
    override val minDate: MutableLiveData<Long> = _minDate

    private val _maxDate = MutableLiveData<Long>()
    override val maxDate: MutableLiveData<Long> = _maxDate


    override fun addBody(body: String) {
        _body.value = body
    }

    override fun addName(name: String) {
        _name.value = name
    }

    override fun addMinDate(minDate: Long) {
        _minDate.value = minDate
    }

    override fun addMaxDate(maxDate: Long) {
        _maxDate.value = maxDate
    }
}