package com.intive.patronage.smarthome.feature.blind.binding

import android.view.MotionEvent
import androidx.databinding.BindingAdapter
import com.intive.patronage.smarthome.feature.blind.view.BlindView
import com.intive.patronage.smarthome.feature.blind.viewmodel.BlindDetailsViewModel

@BindingAdapter("onTouch")
fun setOnBlindTouchListener(view: BlindView, blindDetailsViewModel: BlindDetailsViewModel) {
    view.setOnTouchListener { _, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE) {
            with(view.changePosition(motionEvent.y)) {
                view.position = view.onePercent * this
                view.invalidate()
                blindDetailsViewModel.position = this
                blindDetailsViewModel.setPercent("${100-this} %")
            }
        }
        true
    }
}