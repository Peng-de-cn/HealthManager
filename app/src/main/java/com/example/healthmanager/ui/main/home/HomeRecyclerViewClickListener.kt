package com.example.healthmanager.ui.main.home

import android.view.View
import com.example.healthmanager.ui.main.home.item.ItemMedicine

interface HomeRecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, medicine: ItemMedicine)
}