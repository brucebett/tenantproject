package com.example.tenantfinderapp

import android.os.Parcel
import android.os.Parcelable

data class HousesData(val title: String, val image: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HousesData> {
        override fun createFromParcel(parcel: Parcel): HousesData {
            return HousesData(parcel)
        }

        override fun newArray(size: Int): Array<HousesData?> {
            return arrayOfNulls(size)
        }
    }

}
