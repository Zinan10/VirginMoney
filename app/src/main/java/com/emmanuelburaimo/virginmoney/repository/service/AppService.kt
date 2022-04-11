package com.emmanuelburaimo.virginmoney.repository.service

import com.emmanuelburaimo.virginmoney.repository.model.PersonaModel
import com.emmanuelburaimo.virginmoney.repository.model.RoomModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppService {

    private val BASE_URL="https://61e947967bc0550017bc61bf.mockapi.io/api/v1/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getPeople(): Single<List<PersonaModel>> {
        return api.getPeople()
    }

    fun getRooms(): Single<List<RoomModel>> {
        return api.getRooms()
    }
}