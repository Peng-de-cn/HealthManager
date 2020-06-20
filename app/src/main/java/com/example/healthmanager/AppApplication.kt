package com.example.healthmanager

import android.app.Application
import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine
import org.jetbrains.anko.doAsync

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val medicineDB = MedicineDatabase.instance(applicationContext)

        doAsync {
            medicineDB.medicineDao().deleteAll()
            if (medicineDB.medicineDao().count() == 0) {
                medicineDB.medicineDao().insert(
                    Medicine(
                        name = "阿司匹林",
                        numberOfTaking = 3,
                        timeOfTaking = "3",
                        takingDose = 1,
                        takingTime1 = "07:00",
                        takingTime2 = "13:00",
                        takingTime3 = "19:00",
                        date = ""
                    )
                )
                medicineDB.medicineDao().insert(
                    Medicine(
                        name = "阿司不匹林",
                        numberOfTaking = 3,
                        timeOfTaking = "3",
                        takingDose = 1,
                        takingTime1 = "06:00",
                        takingTime2 = "12:00",
                        takingTime3 = "18:00",
                        date = ""
                    )
                )
            }
        }
    }
}