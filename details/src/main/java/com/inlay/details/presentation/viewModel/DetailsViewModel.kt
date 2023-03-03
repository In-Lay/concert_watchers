package com.inlay.details.presentation.viewModel

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inlay.details.data.models.DetailsDataModel

abstract class DetailsViewModel : ViewModel() {
    abstract val concertsDetails: LiveData<DetailsDataModel?>
    abstract val favButtonFlag: LiveData<Boolean>

    abstract val textName: LiveData<String>
    abstract val textDate: LiveData<String>
    abstract val textLocation: LiveData<String>
    abstract val textDescription: LiveData<String>
    abstract val imageUrl: LiveData<String>

    abstract val iconImage: LiveData<Drawable>
    abstract fun setIconImage(icon: Drawable)

    abstract fun assignDetailsData(detailsDetailsDataModel: DetailsDataModel?)

    abstract fun onFavPressed()
}