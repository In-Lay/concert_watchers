package com.inlay.details.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

class DetailsScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : NestedScrollView(context, attrs) {
    private var shouldStopInterceptingTouchEvent = false

    fun setShouldStopInterceptingTouchEvent(shouldStopInterceptingTouchEvent: Boolean) {
        this.shouldStopInterceptingTouchEvent = shouldStopInterceptingTouchEvent
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (shouldStopInterceptingTouchEvent) {
            false
        } else {
            super.onInterceptTouchEvent(ev)
        }
    }
}