package com.example.healthmanager.ui.inventory

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class InventoryViewModel(inventory: Int): ViewModel() {

    val inventoryString = ObservableField<String>(inventory.toString())

    fun setInventory(number: Int) {
        inventoryString.set(number.toString())
    }
}