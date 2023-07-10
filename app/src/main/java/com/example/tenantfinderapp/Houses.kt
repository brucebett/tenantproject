package com.example.tenantfinderapp

import android.os.Parcel
import android.os.Parcelable

data class Houses(val title: String, val image: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Houses> {
        override fun createFromParcel(parcel: Parcel): Houses {
            return Houses(parcel)
        }

        override fun newArray(size: Int): Array<Houses?> {
            return arrayOfNulls(size)
        }
    }

}
