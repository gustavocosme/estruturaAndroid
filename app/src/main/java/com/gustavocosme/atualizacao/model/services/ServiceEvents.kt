package com.gustavocosme.atualizacao.model.services

import com.gustavocosme.atualizacao.model.data.DataEvents
import retrofit2.Call
import retrofit2.http.GET


interface ServiceEvents {

    @GET("obj.json")
    fun getEvents():Call<DataEvents>



}