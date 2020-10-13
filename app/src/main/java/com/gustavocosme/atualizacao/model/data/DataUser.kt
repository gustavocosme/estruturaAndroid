package com.gustavocosme.atualizacao.model.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataUser(


    @Json(name = "status")
    val status:Boolean? = null,

    @Json(name = "message")
    val message:String? = null,

    @Json(name = "data")
    val data:DataUserData? = null

)




