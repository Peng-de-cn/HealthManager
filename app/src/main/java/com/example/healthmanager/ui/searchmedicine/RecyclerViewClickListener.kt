package com.example.healthmanager.ui.searchmedicine

import android.view.View
import com.example.healthmanager.data.database.entity.Medicine

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, medicine: Medicine)
}