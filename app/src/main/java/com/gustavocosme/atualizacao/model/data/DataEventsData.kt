package com.gustavocosme.atualizacao.model.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataEventsData(

    @Json(name = "name")
    var name:String? = null



)




