package com.example.healthmanager.data.model

data class MedicineModel(
    val id: Long,
    val name: String,
    val numberOfTaking: Int,
    val timeOfTaking: String,
    val takingDose: Int,
    val takingTime1: String,
    val takingTime2: String,
    val takingTime3: String,
    val takingTime4: String,
    val takingTime5: String,
    val date: String
)