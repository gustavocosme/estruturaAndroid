package com.gustavocosme.atualizacao.model.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataEvents(


    @Json(name = "status")
    val status:Boolean? = null,

    @Json(name = "mesage")
    val mesage:String? = null,

    @Json(name = "code")
    val code:Int? = null,

    @Json(name = "data")
    val data:List<DataEventsData>? = null

)




