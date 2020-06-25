package com.example.healthmanager.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.data.database.MyMedicineDatabase

class HomeViewModelFactory(private val medicineDB: MyMedicineDatabase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(medicineDB) as T
    }
}