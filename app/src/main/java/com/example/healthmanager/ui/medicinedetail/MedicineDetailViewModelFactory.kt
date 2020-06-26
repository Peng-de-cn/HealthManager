package com.example.healthmanager.ui.medicinedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MedicineDetailViewModelFactory():  ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MedicineDetailViewModel() as T
    }
}