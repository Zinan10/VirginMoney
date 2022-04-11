package com.emmanuelburaimo.virginmoney.repository.service

import com.emmanuelburaimo.virginmoney.repository.model.PersonaModel
import com.emmanuelburaimo.virginmoney.repository.model.RoomModel
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("rooms")
    fun getRooms(): Single<List<RoomModel>>

    @GET("people")
    fun getPeople(): Single<List<PersonaModel>>
}