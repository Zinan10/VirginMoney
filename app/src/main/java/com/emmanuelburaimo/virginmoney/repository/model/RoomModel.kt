package com.emmanuelburaimo.virginmoney.repository.model

import com.google.gson.annotations.SerializedName

class RoomModel(

    @SerializedName("id")
    val id: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("isOccupied")
    val isOccupied: Boolean?,

    @SerializedName("maxOccupancy")
    val maxOccupancy: String?
)