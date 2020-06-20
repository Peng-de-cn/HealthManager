package com.example.healthmanager.ui.addmedicine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthmanager.L
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.data.repository.MedicineRepository
import com.example.healthmanager.util.Coroutines
import kotlinx.coroutines.Job

class AddMedicineViewModel(private val repository: MedicineRepository) : ViewModel() {

    private lateinit var job: Job

    private var medicines = MutableLiveData<List<Medicine>>()
    val medicinesLiveData: LiveData<List<Medicine>>
        get() = medicines

    fun searchMedicine(name: String) {
        L.d("search: $name")
        job = Coroutines.ioThenMain(
            { repository.medicines(name) },
            { medicines.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}

