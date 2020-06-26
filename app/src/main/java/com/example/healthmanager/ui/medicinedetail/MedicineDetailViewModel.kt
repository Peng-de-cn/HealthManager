package com.example.healthmanager.ui.medicinedetail

import androidx.lifecycle.ViewModel
import com.example.healthmanager.data.database.MyMedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import com.example.healthmanager.util.AppConstants
import org.jetbrains.anko.doAsync

class MedicineDetailViewModel: ViewModel() {

    fun updateMedicine(myMedicineDB: MyMedicineDatabase, medicine: Medicine, resultCode: Int, time: String, dose: Int) {
        when(resultCode) {
            AppConstants.REQUEST_CODE_CUSTOMREMIND1 -> {
                doAsync {
                    medicine.takingTime1 = time
                    medicine.takingDose1 = dose
                    myMedicineDB.myMedicineDao().updateMedicine(medicine)
                }
            }
            AppConstants.REQUEST_CODE_CUSTOMREMIND2 -> {
                doAsync {
                    medicine.takingTime2 = time
                    medicine.takingDose2 = dose
                    myMedicineDB.myMedicineDao().updateMedicine(medicine)
                }
            }
            AppConstants.REQUEST_CODE_CUSTOMREMIND3 -> {
                doAsync {
                    medicine.takingTime3 = time
                    medicine.takingDose3 = dose
                    myMedicineDB.myMedicineDao().updateMedicine(medicine)
                }
            }
            AppConstants.REQUEST_CODE_CUSTOMREMIND4 -> {
                doAsync {
                    medicine.takingTime4 = time
                    medicine.takingDose4 = dose
                    myMedicineDB.myMedicineDao().updateMedicine(medicine)
                }
            }
            AppConstants.REQUEST_CODE_CUSTOMREMIND5 -> {
                doAsync {
                    medicine.takingTime5 = time
                    medicine.takingDose5 = dose
                    myMedicineDB.myMedicineDao().updateMedicine(medicine)
                }
            }
        }
    }

}