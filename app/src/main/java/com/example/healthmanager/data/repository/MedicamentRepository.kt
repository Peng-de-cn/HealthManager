package com.example.healthmanager.data.repository

import com.example.healthmanager.data.database.MedicineDatabase
import com.example.healthmanager.data.database.entity.Medicine

class MedicineRepository(var medicineDB: MedicineDatabase) {

    fun medicines(name: String): List<Medicine> {
        return medicineDB.medicineDao().medicineByName(name)
    }
}