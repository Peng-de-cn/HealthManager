package com.example.healthmanager.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthmanager.data.database.MyMedicineDatabase
import com.example.healthmanager.ui.main.home.item.ItemMedicine
import com.example.healthmanager.util.Coroutines
import kotlinx.coroutines.Job

class HomeViewModel(private val medicineDB: MyMedicineDatabase) : ViewModel() {

    private lateinit var job: Job

    private val medicines = ArrayList<ItemMedicine>()
    val medicinesLiveData = MutableLiveData<List<ItemMedicine>>()

    fun loadItemMedicine() {
        job = Coroutines.ioThenMain({
             medicineDB.myMedicineDao().allmedicine()
        },
            {
                for (myMedicine in it!!) {
                    if (myMedicine.takingTime1!!.isNotEmpty()) {
                        medicines.add(ItemMedicine(myMedicine.name, myMedicine.takingTime1, myMedicine.takingDose1))
                    }
                    if (myMedicine.takingTime2!!.isNotEmpty()) {
                        medicines.add(ItemMedicine(myMedicine.name, myMedicine.takingTime2, myMedicine.takingDose2))
                    }
                    if (myMedicine.takingTime3!!.isNotEmpty()) {
                        medicines.add(ItemMedicine(myMedicine.name, myMedicine.takingTime3, myMedicine.takingDose3))
                    }
                    if (myMedicine.takingTime4!!.isNotEmpty()) {
                        medicines.add(ItemMedicine(myMedicine.name, myMedicine.takingTime4, myMedicine.takingDose4))
                    }
                    if (myMedicine.takingTime5!!.isNotEmpty()) {
                        medicines.add(ItemMedicine(myMedicine.name, myMedicine.takingTime5, myMedicine.takingDose5))
                    }
                    medicinesLiveData.value = medicines
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}