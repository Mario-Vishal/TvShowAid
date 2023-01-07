package com.example.tvshowaid.models

import android.os.Parcel
import android.os.Parcelable

data class TvShow(
    var country: String? ="",
    var end_date: String?="",
    var id: Int=-1,
    var image_thumbnail_path: String? ="",
    var name: String? ="",
    var network: String? ="",
    var permalink: String? ="",
    var start_date: String? ="",
    var status: String? =""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeString(end_date)
        parcel.writeInt(id)
        parcel.writeString(image_thumbnail_path)
        parcel.writeString(name)
        parcel.writeString(network)
        parcel.writeString(permalink)
        parcel.writeString(start_date)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TvShow> {
        override fun createFromParcel(parcel: Parcel): TvShow {
            return TvShow(parcel)
        }

        override fun newArray(size: Int): Array<TvShow?> {
            return arrayOfNulls(size)
        }
    }

}