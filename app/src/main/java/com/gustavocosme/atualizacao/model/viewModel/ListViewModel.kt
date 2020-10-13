package com.gustavocosme.atualizacao.model.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gustavocosme.atualizacao.model.Api
import com.gustavocosme.atualizacao.model.data.DataEvents
import com.gustavocosme.atualizacao.model.data.DataEventsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ListViewModel:ViewModel()
{

    val dadosLibeData:MutableLiveData<List<DataEventsData>> = MutableLiveData();

    fun getDados()
    {


        Api.service.getEvents().enqueue(object : Callback<DataEvents>{

            override fun onResponse(
                call: Call<DataEvents>,
                response: Response<DataEvents>
            ){

                if(response.isSuccessful)
                {

                    try {

                        dadosLibeData.value = response.body()!!.data!!

                    }catch (e:Exception)
                    {

                    }

                }


            }

            override fun onFailure(call: Call<DataEvents>, t: Throwable) {

                Log.e("ERRO",t.message);

            }


        })








    }





}