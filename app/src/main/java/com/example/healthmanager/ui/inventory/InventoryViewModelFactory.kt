package com.example.healthmanager.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class InventoryViewModelFactory(private val inventory: Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InventoryViewModel(inventory) as T
    }
}