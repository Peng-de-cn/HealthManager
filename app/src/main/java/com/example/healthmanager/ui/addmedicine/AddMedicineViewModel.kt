package com.example.healthmanager.ui.addmedicine

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.transition.TransitionManager
import com.example.healthmanager.L
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.data.repository.MedicineRepository
import com.example.healthmanager.util.Coroutines
import com.example.healthmanager.util.RotateArrow
import kotlinx.coroutines.Job

class AddMedicineViewModel(
    private val repository: MedicineRepository,
    private val transitionsContainer: ConstraintLayout,
    private val imageArrow: ImageView
): ViewModel() {

    private lateinit var job: Job

    private var medicines = MutableLiveData<List<Medicine>>()
    val medicinesLiveData: LiveData<List<Medicine>>
        get() = medicines

    val medicineSelected: ObservableBoolean = ObservableBoolean(false)
    val showDetails: ObservableBoolean = ObservableBoolean(true)
    val onMedicineEditClicked = { onMedicineEditClicked() }
    val onArrowClicked = { onArrowClicked() }

    fun searchMedicine(name: String) {
        L.d("search: $name")
        job = Coroutines.ioThenMain(
            { repository.medicines(name.trim()) },
            { medicines.value = it }
        )
    }

    private fun onMedicineEditClicked() {
        medicineSelected.set(false)
    }

    private fun onArrowClicked() {
        TransitionManager.beginDelayedTransition(transitionsContainer);

        if (showDetails.get()) {
            showDetails.set(false)
            RotateArrow(imageArrow, false)
        } else {
            showDetails.set(true)
            RotateArrow(imageArrow, true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}