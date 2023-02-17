package com.inlay.concertswatcher.presentation.mainList.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.presentation.mainList.asDetailsData
import com.inlay.concertswatcher.presentation.search.viewModel.SearchViewModel
import com.inlay.details.data.models.DetailsDataModel

class AppMainListViewModel(private val searchViewModel: SearchViewModel) : MainListViewModel() {

    override val error: LiveData<String> = searchViewModel.searchError

    override val isLoading: LiveData<Boolean> = searchViewModel.searchIsLoading

    private var _concertsData = searchViewModel.searchConcertsData
    override val concertsData: LiveData<ConcertsDataNetworkModel> = _concertsData


    private val _itemNetworkModelMutableLiveData = MutableLiveData<DetailsDataModel?>()
    override val itemNetworkModelMutableLiveData: LiveData<DetailsDataModel?> =
        _itemNetworkModelMutableLiveData


    init {
//        searchViewModel.sendSearchUiModel(SearchUiModel("artist", "Ed Sheeran", null, null))
        Log.d("DetailsFlag", "MainListVM init; concertsData: ${concertsData.value}")
        Log.d(
            "DetailsFlag",
            "MainListVM init; searchConcertsData: ${searchViewModel.searchConcertsData.value}"
        )
    }

    override fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel) {
//        Log.d(
//            "DetailsFlag",
//            "MainListVM addTempConcerts; concertsData from searchVM BEFORE: ${searchViewModel.searchConcertsData.value}"
//        )
        Log.d(
            "DetailsFlag",
            "MainListVM addTempConcerts; concertsDataNetworkModel: $concertsDataNetworkModel"
        )
        Log.d(
            "DetailsFlag",
            "MainListVM addTempConcerts; concertsData BEFORE: ${concertsData.value}"
        )
        searchViewModel.addTempConcerts(concertsDataNetworkModel)
//        Log.d(
//            "DetailsFlag",
//            "MainListVM addTempConcerts; concertsData from searchVM AFTER: ${searchViewModel.searchConcertsData.value}"
//        )
        Log.d(
            "DetailsFlag",
            "MainListVM addTempConcerts; concertsData AFTER: ${concertsData.value}"
        )
    }

    override fun goToDetails(itemData: ConcertItemNetworkModel?) {
        _itemNetworkModelMutableLiveData.value = itemData?.asDetailsData()
    }

    override fun initConcertsData() {
        Log.d(
            "DetailsFlag",
            "MainListVM initConcertsData; concertsData from searchVM BEFORE: ${searchViewModel.searchConcertsData.value}"
        )
        _concertsData = searchViewModel.searchConcertsData
        Log.d(
            "DetailsFlag",
            "MainListVM initConcertsData; concertsData from searchVM AFTER: ${searchViewModel.searchConcertsData.value}"
        )
    }

    override fun setItemModelToNull() {
        _itemNetworkModelMutableLiveData.value = null
    }
}