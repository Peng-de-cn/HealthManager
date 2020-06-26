package com.example.healthmanager.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthmanager.data.database.MyMedicineDatabase
import com.example.healthmanager.ui.main.home.item.ItemMedicine
import com.example.healthmanager.util.Coroutines
import kotlinx.coroutines.Job
import org.jetbrains.anko.doAsync

class HomeViewModel(private val medicineDB: MyMedicineDatabase) : ViewModel() {

    private lateinit var job: Job

    private val medicines = ArrayList<ItemMedicine>()
    val medicinesLiveData = MutableLiveData<List<ItemMedicine>>()

    fun loadItemMedicine() {
        job = Coroutines.ioThenMain({
            medicineDB.myMedicineDao().allmedicine()
        },
            {
                medicines.clear()
                for (myMedicine in it!!) {
                    if (myMedicine.takingTime1!!.isNotEmpty()) {
                        val item = ItemMedicine(
                            myMedicine,
                            myMedicine.name,
                            myMedicine.takingTime1,
                            myMedicine.takingDose1,
                            1
                        )
                        medicines.add(item)
                    }
                    if (myMedicine.takingTime2!!.isNotEmpty()) {
                        val item = ItemMedicine(
                            myMedicine,
                            myMedicine.name,
                            myMedicine.takingTime2,
                            myMedicine.takingDose2,
                            2
                        )
                        medicines.add(item)
                    }
                    if (myMedicine.takingTime3!!.isNotEmpty()) {
                        val item = ItemMedicine(
                            myMedicine,
                            myMedicine.name,
                            myMedicine.takingTime3,
                            myMedicine.takingDose3,
                            3
                        )
                        medicines.add(item)
                    }
                    if (myMedicine.takingTime4!!.isNotEmpty()) {
                        val item = ItemMedicine(
                            myMedicine,
                            myMedicine.name,
                            myMedicine.takingTime4,
                            myMedicine.takingDose4,
                            4
                        )
                        medicines.add(item)
                    }
                    if (myMedicine.takingTime5!!.isNotEmpty()) {
                        val item = ItemMedicine(
                            myMedicine,
                            myMedicine.name,
                            myMedicine.takingTime5,
                            myMedicine.takingDose5,
                            5
                        )
                        medicines.add(item)
                    }

                    doAsync {
                        if (myMedicine.takingTime1!!.isEmpty() && myMedicine.takingTime2!!.isEmpty()
                            && myMedicine.takingTime3!!.isEmpty() && myMedicine.takingTime4!!.isEmpty()
                            && myMedicine.takingTime5!!.isEmpty()) {
                            medicineDB.myMedicineDao().delete(myMedicine)
                        }
                    }
                }
                medicinesLiveData.value = medicines
            })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}