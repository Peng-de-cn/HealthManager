package com.example.healthmanager.ui.customremind

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class CustomRemindViewModel(takingDose: Int): ViewModel() {

    val takingDoseString = ObservableField<String>(takingDose.toString())

    fun setTakingDose(number: Int) {
        takingDoseString.set(number.toString())
    }
}