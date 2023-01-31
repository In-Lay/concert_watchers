package com.inlay.concertswatcher.presentation.mainList

import android.view.View
import com.inlay.concertswatcher.data.Data

interface DetailsClickListener {
    fun detailsClicked(view: View, data: Data)
}