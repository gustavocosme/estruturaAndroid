package com.gustavocosme.atualizacao.model

import com.gustavocosme.atualizacao.model.services.ServiceEvents
import com.gustavocosme.atualizacao.model.services.ServicePet
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


enum class SERVER(val value: String){
    URL_RELEASE("http://mundosafari.com.br/mobile/test/"),
    URL_DEBUG("http://mundosafari.com.br/mobile/test/"),
    URL_PET("https://pet.safarihost.com.br/api/"),
    CEP("https://viacep.com.br/ws/")
}

object Api {

    private fun e(url:SERVER):Retrofit
    {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);



        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()



        return Retrofit.Builder()
            .baseUrl(url.value)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }

    var service = e(SERVER.URL_RELEASE).create(ServiceEvents::class.java);
    var servicePet = e(SERVER.URL_PET).create(ServicePet::class.java);

}