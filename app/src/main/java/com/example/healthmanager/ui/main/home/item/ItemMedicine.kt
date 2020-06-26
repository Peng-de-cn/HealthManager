package com.example.healthmanager.ui.main.home.item

import android.os.Parcel
import android.os.Parcelable
import com.example.healthmanager.data.database.entity.Medicine
import java.util.*

data class ItemMedicine(
    var medicine: Medicine,
    var name: String? = "",
    var takingTime: String? = "",
    var takingDose: Int? = 0,
    var takingCount: Int? = 0
): Parcelable, Comparable<ItemMedicine> {
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

    override fun compareTo(other: ItemMedicine): Int {
        return if (this.takingTime!!.isNotEmpty() && other.takingTime!!.isNotEmpty()) {
            val time1 = takingTime(this.takingTime!!)
            val time2 = takingTime(other.takingTime!!)
            (time1 - time2).toInt()
        } else {
            0
        }
    }

    private fun takingTime(takingTime: String): Long {
        val time = takingTime.split(":".toRegex()).toTypedArray()
        val hour = time[0].toInt()
        val minute = time[1].toInt()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}