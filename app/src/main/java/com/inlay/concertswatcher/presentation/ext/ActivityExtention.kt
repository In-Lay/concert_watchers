package com.inlay.concertswatcher.presentation.ext

import android.content.Context
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.FragmentActivity

fun Context.getFragmentActivity(): FragmentActivity {
    return when (this) {
        is FragmentActivity -> this
        is ContextThemeWrapper -> this.baseContext as FragmentActivity
        else -> (this as android.view.ContextThemeWrapper).baseContext as FragmentActivity
    }
}
