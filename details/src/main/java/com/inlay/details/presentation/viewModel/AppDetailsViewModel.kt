package com.inlay.details.presentation.viewModel

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import com.inlay.details.data.models.DetailsDataModel
import com.inlay.details.data.models.DetailsLocationModel
import com.inlay.details.data.utils.longDateFormatter
import com.inlay.details.data.utils.longDateParser
import com.inlay.details.data.utils.shortDateFormatter
import com.inlay.details.data.utils.shortDateParser

class AppDetailsViewModel : DetailsViewModel() {
    private val _concertsDetails = MutableLiveData<DetailsDataModel?>()
    override val concertsDetails: LiveData<DetailsDataModel?> = _concertsDetails

    private val _favButtonFlag = MutableLiveData<Boolean>()
    override val favButtonFlag: LiveData<Boolean> = _favButtonFlag

    private var _textName = MutableLiveData<String>()
    override val textName = _textName

    private var _textDate = MutableLiveData<String>()
    override val textDate = _textDate

    private var _textLocation = MutableLiveData<String>()
    override val textLocation = _textLocation

    private var _textDescription = MutableLiveData<String>()
    override val textDescription = _textDescription

    private var _imageUrl = MutableLiveData<String>()
    override val imageUrl = _imageUrl

    private val _iconImage = MutableLiveData<Drawable>()
    override val iconImage: LiveData<Drawable> = _iconImage

    override fun setIconImage(icon: Drawable) {
        _iconImage.value = icon
    }

    override fun assignDetailsData(detailsDetailsDataModel: DetailsDataModel?) {
        _concertsDetails.value = detailsDetailsDataModel
        detailsDetailsDataModel?.let {
            _textName.value = it.name
            _textDate.value = if (it.startDate.length > 10) {
                val parsedDate = longDateParser.parse(it.startDate)
                longDateFormatter.format(parsedDate!!)
            } else {
                val parsedDate = shortDateParser.parse(it.startDate)
                shortDateFormatter.format(parsedDate!!)
            }

            it.detailsLocationModel.let { locationModel: DetailsLocationModel ->
                _textLocation.value =
                    "${locationModel.detailsAddressModel.addressCountry}, " + "${locationModel.detailsAddressModel.streetAddress} Str. " + "\n${locationModel.name}"
            }
            _textDescription.value = it.description
            _imageUrl.value = it.image
        }
    }

    override fun onFavPressed() {
        _favButtonFlag.value = _favButtonFlag.value == false || _favButtonFlag.value == null
    }


    companion object {
        @JvmStatic
        @BindingAdapter("imageSource")
        fun loadImage(view: ImageView, image: String) {
            view.load(image)
        }

        @JvmStatic
        @BindingAdapter("iconImage")
        fun loadIconImage(view: ImageView, icon: Drawable) {
            view.load(icon)
        }
    }
}