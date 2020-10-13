package com.gustavocosme.atualizacao.presentation.autentication

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.gustavocosme.atualizacao.R
import com.gustavocosme.atualizacao.dialog.DialogLoad
import com.gustavocosme.atualizacao.model.Api
import com.gustavocosme.atualizacao.model.data.DataUser
import com.gustavocosme.atualizacao.altran.util.Dialogs
import com.gustavocosme.atualizacao.altran.form.FormValidation
import com.gustavocosme.atualizacao.altran.util.Teclado
import com.gustavocosme.atualizacao.altran.ui.Ac
import kotlinx.android.synthetic.main.autenticacao_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : Ac() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.autenticacao_login);

        Teclado.removeThis(this);
        addEvents();


    }

    //MODEL EVENTS

    private fun addEvents() {

        btnLogin.setOnClickListener {

            initModel();

        }

        scroll.setOnClickListener {

            Teclado.removeThis(this);

        }

        txtSenha.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_SEND) {

                initModel();
                return@OnEditorActionListener true
            }
            false
        })




    }


    //VALIDATION

    fun isValidation():Boolean
    {

        if(txtEmail.text.toString().count() == 0)
        {

            Dialogs.add(this,"Preencha o campo e-mail.");

            return false;
        }

        if(!FormValidation.checkEmail(txtEmail.text.toString()))
        {
            Dialogs.add(this,"E-mail inválido.");
            return false;
        }

        if(txtSenha.text.toString().count() == 0)
        {
            Dialogs.add(this,"Preencha o campo senha.");
            return false;
        }

        if(txtSenha.text.toString().count() < 6)
        {
            Dialogs.add(this,"Senha com no mínimo 6 caracteres.");
            return false;
        }

        return true;

    }


    //MODEL

    fun initModel(){

        Teclado.removeThis(this);

        if(!isValidation())
            return;


        var d = DialogLoad(this);
        d.show();
        btnLogin.isEnabled = false;

        Api.servicePet.postLogin(txtEmail.text.toString(),txtSenha.text.toString()).enqueue(object : Callback<DataUser> {

            override fun onResponse(
                call: Call<DataUser>,
                response: Response<DataUser>
            ){

                d.dismiss();
                btnLogin.isEnabled = true;

                try
                {

                    if(response.isSuccessful)
                    {

                        if(response.body()!!.status!!)
                        {
                            Log.i("TOKEN",response.body()!!.data!!.token);

                        }else{

                            Dialogs.add(this@Login,response.body()!!.data!!.token);

                        }

                    }else{

                        Dialogs.add(this@Login,"Estamos melhorando nossos serviços.");

                    }

                }catch (e:Exception){

                     Log.e("ERRO",e.message);
                     Dialogs.add(this@Login,"Estamos melhorando nossos serviços.");



                }




            }

            override fun onFailure(call: Call<DataUser>, t: Throwable) {

                d.dismiss();

            }


        })




    }





}//END CLASS
