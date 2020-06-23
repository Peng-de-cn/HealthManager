package com.example.healthmanager.data.database.entity

import android.os.Parcel
import android.os.Parcelable
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
    @ColumnInfo(name = "takingDose1")
    var takingDose1: Int? = 0,
    @ColumnInfo(name = "takingDose2")
    var takingDose2: Int? = 0,
    @ColumnInfo(name = "takingDose3")
    var takingDose3: Int? = 0,
    @ColumnInfo(name = "takingDose4")
    var takingDose4: Int? = 0,
    @ColumnInfo(name = "takingDose5")
    var takingDose5: Int? = 0,
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
    var date: String? = "",
    @ColumnInfo(name = "inventory")
    var inventory: Int? = 0,
    @ColumnInfo(name = "inventoryLeft")
    var inventoryLeft: Int? = 0,
    @ColumnInfo(name = "details")
    var details: String? = ""

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeValue(numberOfTaking)
        parcel.writeString(timeOfTaking)
        parcel.writeValue(takingDose1)
        parcel.writeValue(takingDose2)
        parcel.writeValue(takingDose3)
        parcel.writeValue(takingDose4)
        parcel.writeValue(takingDose5)
        parcel.writeString(takingTime1)
        parcel.writeString(takingTime2)
        parcel.writeString(takingTime3)
        parcel.writeString(takingTime4)
        parcel.writeString(takingTime5)
        parcel.writeString(date)
        parcel.writeValue(inventory)
        parcel.writeValue(inventoryLeft)
        parcel.writeString(details)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicine> {
        override fun createFromParcel(parcel: Parcel): Medicine {
            return Medicine(parcel)
        }

        override fun newArray(size: Int): Array<Medicine?> {
            return arrayOfNulls(size)
        }
    }
}