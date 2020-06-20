package com.example.healthmanager.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun bindOnClick(view: View, handler: () -> Any) = view.setOnClickListener { handler() }
