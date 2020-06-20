package com.example.healthmanager.ui.searchmedicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.data.repository.MedicineRepository

@Suppress("UNCHECKED_CAST")
class SearchMedicineViewModelFactory(private val repository: MedicineRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchMedicineViewModel(repository) as T
    }
}