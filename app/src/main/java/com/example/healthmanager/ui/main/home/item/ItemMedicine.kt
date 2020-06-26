package com.example.healthmanager.ui.main.home.item

import android.os.Parcel
import android.os.Parcelable
import com.example.healthmanager.data.database.entity.Medicine

data class ItemMedicine(
    var medicine: Medicine,
    var name: String? = "",
    var takingTime: String? = "",
    var takingDose: Int? = 0,
    var takingCount: Int? = 0
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Medicine::class.java.classLoader)!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(medicine, flags)
        parcel.writeString(name)
        parcel.writeString(takingTime)
        parcel.writeValue(takingDose)
        parcel.writeValue(takingCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemMedicine> {
        override fun createFromParcel(parcel: Parcel): ItemMedicine {
            return ItemMedicine(parcel)
        }

        override fun newArray(size: Int): Array<ItemMedicine?> {
            return arrayOfNulls(size)
        }
    }
}