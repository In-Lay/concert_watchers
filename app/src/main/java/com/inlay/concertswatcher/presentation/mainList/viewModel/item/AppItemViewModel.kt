package com.inlay.concertswatcher.presentation.mainList.viewModel.item

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.presentation.mainList.viewModel.MainListViewModel
import com.inlay.concertswatcher.utils.longDateFormatter
import com.inlay.concertswatcher.utils.longDateParser
import com.inlay.concertswatcher.utils.shortDateFormatter
import com.inlay.concertswatcher.utils.shortDateParser
import java.util.*

class AppItemViewModel(private val mainListViewModel: MainListViewModel) : ItemViewModel() {

    override val textName = MutableLiveData<String>()

    override val textLocation = MutableLiveData<String>()

    override val textDate = MutableLiveData<String>()

    override val imageUrl = MutableLiveData<String>()

    private val _itemNetworkModelMutableLiveData = MutableLiveData<ConcertItemNetworkModel?>()
    override val itemNetworkModelMutableLiveData: LiveData<ConcertItemNetworkModel?> =
        _itemNetworkModelMutableLiveData


    override fun goToDetails() {
        mainListViewModel.goToDetails(_itemNetworkModelMutableLiveData.value)
    }

    override fun initItemData(itemData: ConcertItemNetworkModel?) {

        _itemNetworkModelMutableLiveData.value = itemData
        itemData?.let {
            textName.value = it.name
            textLocation.value = it.concertLocationNetworkModel.name

            textDate.value = if (it.startDate.length > 10) {
                val unformattedDate = longDateParser.parse(it.startDate)
                longDateFormatter.format(unformattedDate!!)
            } else {
                val unformattedDate = shortDateParser.parse(it.startDate)
                shortDateFormatter.format(unformattedDate!!)
            }

            imageUrl.value = it.image
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