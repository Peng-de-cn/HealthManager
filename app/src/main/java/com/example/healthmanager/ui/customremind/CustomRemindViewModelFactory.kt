package com.example.healthmanager.ui.customremind

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.data.database.entity.Medicine

@Suppress("UNCHECKED_CAST")
class CustomRemindViewModelFactory(private val takingDose: Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CustomRemindViewModel(takingDose) as T
    }
}