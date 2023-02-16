package com.inlay.concertswatcher.presentation

import android.app.Activity
import com.inlay.details.DetailsActivity
import com.inlay.details.data.models.DetailsDataModel

class AppNavigator(private val activity: Activity) : Navigator {
    override fun goToDetails(dataItem: DetailsDataModel) {
        val intent = DetailsActivity.getStartIntent(activity)
        intent.putExtra("dataModelItem", dataItem)
        activity.startActivity(intent)
    }
}