package com.inlay.concertswatcher.presentation

import com.inlay.details.data.models.DetailsDataModel

interface Navigator {
    fun goToDetails(dataItem: DetailsDataModel)
}