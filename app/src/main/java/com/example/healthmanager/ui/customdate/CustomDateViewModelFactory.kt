package com.example.healthmanager.ui.customdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.data.database.entity.Medicine

@Suppress("UNCHECKED_CAST")
class CustomDateViewModelFactory(private val medicine: Medicine) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CustomDateViewModel(medicine) as T
    }
}