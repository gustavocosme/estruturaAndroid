package com.gustavocosme.atualizacao.model.services

import com.gustavocosme.atualizacao.model.data.DataUser
import retrofit2.Call
import retrofit2.http.*


interface ServicePet {



    @POST("user/login")
    @FormUrlEncoded
    fun postLogin(@Field("email") email:String,
                  @Field("password") password:String,
                  @Field("profile_id") profile_id:Int=1):Call<DataUser>


}