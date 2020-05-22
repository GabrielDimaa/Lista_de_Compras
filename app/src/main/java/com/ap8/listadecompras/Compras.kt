package com.example.clientesapp

import android.os.Parcel
import android.os.Parcelable

class Compras(var id: Int?, val produto:String?, val quantidade: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(produto)
        parcel.writeString(quantidade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Compras> {
        override fun createFromParcel(parcel: Parcel): Compras {
            return Compras(parcel)
        }

        override fun newArray(size: Int): Array<Compras?> {
            return arrayOfNulls(size)
        }
    }
}