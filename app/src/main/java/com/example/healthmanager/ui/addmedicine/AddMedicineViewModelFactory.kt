package com.example.healthmanager.ui.addmedicine

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthmanager.data.database.entity.Medicine

@Suppress("UNCHECKED_CAST")
class AddMedicineViewModelFactory(
    private val medicine: Medicine,
    private val transitionsContainer: ConstraintLayout,
    private val imageArrow: ImageView
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddMedicineViewModel(medicine, transitionsContainer, imageArrow) as T
    }
}