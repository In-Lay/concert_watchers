package com.inlay.concertswatcher.presentation.mainList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.di.getOrCreateSearchScope
import com.inlay.concertswatcher.presentation.mainList.asDetailsData
import com.inlay.concertswatcher.presentation.search.viewModel.SearchViewModel
import com.inlay.details.data.models.DetailsDataModel

class AppMainListViewModel : MainListViewModel() {

    private val searchViewModel: SearchViewModel = getOrCreateSearchScope().get()

    override val error: LiveData<String> = searchViewModel.searchError

    override val isLoading: LiveData<Boolean> = searchViewModel.searchIsLoading

    override val concertsData: LiveData<ConcertsDataNetworkModel> =
        searchViewModel.searchConcertsData


    private val _itemNetworkModelMutableLiveData = MutableLiveData<DetailsDataModel?>()
    override val itemNetworkModelMutableLiveData: LiveData<DetailsDataModel?> =
        _itemNetworkModelMutableLiveData


    init {
//        searchViewModel.sendSearchUiModel(SearchUiModel("artist", "Ed Sheeran", null, null))
    }

    override fun addTempConcerts(concertsDataNetworkModel: ConcertsDataNetworkModel) {

        searchViewModel.addTempConcerts(concertsDataNetworkModel)
    }

    override fun goToDetails(itemData: ConcertItemNetworkModel?) {
        _itemNetworkModelMutableLiveData.value = itemData?.asDetailsData()
    }

    override fun setItemDataToNull() {
        _itemNetworkModelMutableLiveData.value = null
    }
}