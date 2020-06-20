package com.example.healthmanager.ui.addmedicine.item

import android.os.Parcel
import android.os.Parcelable

data class MedicineItem(val id: Long = -1, var name: String = "", var numberOfTaking: Int = 0,
                          var timeOfTaking: String = "", var takingDose: Int = 0, var takingTime1: String = "",
                          var takingTime2: String = "", var takingTime3: String = "", var takingTime4: String = "",
                          var takingTime5: String = "", var date: String = ""): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(numberOfTaking)
        parcel.writeString(timeOfTaking)
        parcel.writeInt(takingDose)
        parcel.writeString(takingTime1)
        parcel.writeString(takingTime2)
        parcel.writeString(takingTime3)
        parcel.writeString(takingTime4)
        parcel.writeString(takingTime5)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MedicineItem> {
        override fun createFromParcel(parcel: Parcel): MedicineItem {
            return MedicineItem(parcel)
        }

        override fun newArray(size: Int): Array<MedicineItem?> {
            return arrayOfNulls(size)
        }
    }
}