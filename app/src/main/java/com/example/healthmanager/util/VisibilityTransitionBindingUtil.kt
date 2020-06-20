package com.example.healthmanager.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("transitionVisibility")
fun bindVisibilityTransition(view: View, visibility: Int) {
    if (visibility == View.VISIBLE) view.visibility = View.VISIBLE
    view.animate().alpha(if (visibility == View.VISIBLE) 1f else 0f).withEndAction { view.visibility = visibility }
}