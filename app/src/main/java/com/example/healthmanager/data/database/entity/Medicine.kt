package com.example.healthmanager.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String? = "",
    @ColumnInfo(name = "numberOfTaking")
    var numberOfTaking: Int? = 0,
    @ColumnInfo(name = "timeOfTaking")
    var timeOfTaking: String? = "",
    @ColumnInfo(name = "takingDose")
    var takingDose: Int? = 0,
    @ColumnInfo(name = "takingTime1")
    var takingTime1: String? = "",
    @ColumnInfo(name = "takingTime2")
    var takingTime2: String? = "",
    @ColumnInfo(name = "takingTime3")
    var takingTime3: String? = "",
    @ColumnInfo(name = "takingTime4")
    var takingTime4: String? = "",
    @ColumnInfo(name = "takingTime5")
    var takingTime5: String? = "",
    @ColumnInfo(name = "date")
    var date: String? = ""

){
    constructor() : this(0, "")
}