package com.emmanuelburaimo.virginmoney.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PersonaModel(

    @SerializedName("id")
    val id: String?,

    @SerializedName("firstName")
    val firstName: String?,

    @SerializedName("lastName")
    val lastName: String?,

    @SerializedName("jobtitle")
    val jobtitle: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("avatar")
    val avatar: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("favouriteColor")
    val favouriteColor: String?

):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(jobtitle)
        parcel.writeString(email)
        parcel.writeString(avatar)
        parcel.writeString(createdAt)
        parcel.writeString(favouriteColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonaModel> {
        override fun createFromParcel(parcel: Parcel): PersonaModel {
            return PersonaModel(parcel)
        }

        override fun newArray(size: Int): Array<PersonaModel?> {
            return arrayOfNulls(size)
        }
    }
}