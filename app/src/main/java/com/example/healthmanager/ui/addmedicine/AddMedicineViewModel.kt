package com.example.healthmanager.ui.addmedicine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthmanager.L
import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.data.repository.MedicineRepository
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND1
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND2
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND3
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND4
import com.example.healthmanager.util.AppConstants.Companion.REQUEST_CODE_CUSTOMREMIND5
import com.example.healthmanager.util.Coroutines
import kotlinx.coroutines.Job
import org.jetbrains.anko.doAsync

class AddMedicineViewModel(
    private val repository: MedicineRepository
): ViewModel() {

    private lateinit var job: Job

    val medicinesLiveData = MutableLiveData<List<Medicine>>()

    fun searchMedicine(name: String) {
        L.d("search: $name")
        job = Coroutines.ioThenMain(
            { repository.medicines(name.trim()) },
            { medicinesLiveData.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

    fun updateMedicine(medicineDB: MedicineDatabase, medicine: Medicine, resultCode: Int, time: String, dose: Int) {
        when(resultCode) {
            REQUEST_CODE_CUSTOMREMIND1 -> {
                doAsync {
                    medicine.takingTime1 = time
                    medicine.takingDose1 = dose
                    medicineDB.medicineDao().updateMedicine(medicine)
                }
            }
            REQUEST_CODE_CUSTOMREMIND2 -> {
                doAsync {
                    medicine.takingTime2 = time
                    medicine.takingDose2 = dose
                    medicineDB.medicineDao().updateMedicine(medicine)
                }
            }
            REQUEST_CODE_CUSTOMREMIND3 -> {
                doAsync {
                    medicine.takingTime3 = time
                    medicine.takingDose3 = dose
                    medicineDB.medicineDao().updateMedicine(medicine)
                }
            }
            REQUEST_CODE_CUSTOMREMIND4 -> {
                doAsync {
                    medicine.takingTime4 = time
                    medicine.takingDose4 = dose
                    medicineDB.medicineDao().updateMedicine(medicine)
                }
            }
            REQUEST_CODE_CUSTOMREMIND5 -> {
                doAsync {
                    medicine.takingTime5 = time
                    medicine.takingDose5 = dose
                    medicineDB.medicineDao().updateMedicine(medicine)
                }
            }
        }
    }
}