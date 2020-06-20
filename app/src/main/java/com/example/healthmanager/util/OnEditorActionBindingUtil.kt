package com.example.healthmanager.util

import android.view.KeyEvent
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("onEditorAction")
fun bindOnEditorAction(view: TextView, handler: (id: Int, event: KeyEvent?) -> Any) {
    view.setOnEditorActionListener { _, id, keyEvent ->
        handler(id, keyEvent)
        true
    }
}
