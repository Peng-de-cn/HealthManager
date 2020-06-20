package com.example.healthmanager.ui.addmedicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.data.repository.MedicineRepository

@Suppress("UNCHECKED_CAST")
class AddMedicineViewModelFactory(private val repository: MedicineRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddMedicineViewModel(repository) as T
    }
}