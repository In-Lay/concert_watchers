package com.inlay.concertswatcher.presentation.favouriteList.viewModel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.presentation.mainList.viewModel.item.ItemViewModel
import com.inlay.concertswatcher.utils.longDateFormatter
import com.inlay.concertswatcher.utils.longDateParser
import com.inlay.concertswatcher.utils.shortDateFormatter
import com.inlay.concertswatcher.utils.shortDateParser

class FavouriteItemViewModel(private val favouriteListViewModel: FavouriteListViewModel) :
    ItemViewModel() {
    private val _textName = MutableLiveData<String>()
    override val textName: LiveData<String> = _textName

    private val _textLocation = MutableLiveData<String>()
    override val textLocation: LiveData<String> = _textLocation

    private val _textDate = MutableLiveData<String>()
    override val textDate: LiveData<String> = _textDate

    private val _imageUrl = MutableLiveData<String>()
    override val imageUrl: LiveData<String> = _imageUrl

    private val _itemNetworkModelMutableLiveData = MutableLiveData<ConcertItemNetworkModel?>()
    override val itemNetworkModelMutableLiveData: LiveData<ConcertItemNetworkModel?> =
        _itemNetworkModelMutableLiveData

    override fun goToDetails() {
        favouriteListViewModel.goToDetails(_itemNetworkModelMutableLiveData.value)
    }

    override fun initItemData(itemData: ConcertItemNetworkModel?) {
        _itemNetworkModelMutableLiveData.value = itemData
        itemData?.let {
            _textName.value = it.name

            _textLocation.value = it.concertLocationNetworkModel.name

            _textDate.value = if (it.startDate.length > 10) {
                val unformattedDate = longDateParser.parse(it.startDate)
                longDateFormatter.format(unformattedDate!!)
            } else {
                val unformattedDate = shortDateParser.parse(it.startDate)
                shortDateFormatter.format(unformattedDate!!)
            }

            _imageUrl.value = it.image
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageSource")
        fun loadImage(view: ImageView, image: String) {
            view.load(image)
        }
    }
}