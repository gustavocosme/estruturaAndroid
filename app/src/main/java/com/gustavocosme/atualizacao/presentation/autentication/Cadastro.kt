package com.gustavocosme.atualizacao.presentation.autentication

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.gustavocosme.atualizacao.App
import com.gustavocosme.atualizacao.R
import com.gustavocosme.atualizacao.altran.extensions.initGaleriaUploadPhoto
import com.gustavocosme.atualizacao.altran.extensions.initVideoUploadPhoto
import com.gustavocosme.atualizacao.altran.extensions.setColorStatusBar
import com.gustavocosme.atualizacao.altran.form.FormDocGenerate
import com.gustavocosme.atualizacao.altran.form.FormValidation
import com.gustavocosme.atualizacao.altran.form.FormMask
import com.gustavocosme.atualizacao.altran.ui.Ac
import com.gustavocosme.atualizacao.altran.util.Dialogs
import com.gustavocosme.atualizacao.altran.util.Teclado
import kotlinx.android.synthetic.main.autenticacao_cadastro.*
import java.io.File


class Cadastro : Ac() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.autenticacao_cadastro)

        initTopo("Cadastro");
        setColorStatusBar();

        if (App.IS_TEST) {
            initTest();
        }

        addEvents();

    }

    fun initTest() {


        var nome = FormDocGenerate().getName();
        txtNome.setText(nome);
        txtBairro.setText("Bairro");
        txtCep.setText("51020-280");
        txtCidade.setText("Cidade");
        txtComplemento.setText("Complemento");
        txtCpf.setText(FormDocGenerate().cpf(true));
        txtEmail.setText(nome + "@" + nome + ".com");
        txtEndereco.setText("Endereço");
        txtNumero.setText("10");
        txtTelefone.setText("(00)00000-0000");
        txtSenha.setText("111111");
        txtSenhaC.setText("111111");
        txtEstado.setText("PE");

    }




    //EVENTOS

    private fun addEvents() {

        txtCpf.addTextChangedListener(FormMask.insert("###.###.###-##", txtCpf));
        txtCep.addTextChangedListener(FormMask.insert("#####-###", txtCep));

        initCep();

        btnEstado.setOnClickListener {

            initEstado();
        }



        btn.setOnClickListener {

            initModel();
        }

        cadastroFoto.setOnClickListener {

            onUploadPhoto();
        }

        scroll.setOnClickListener {

            Teclado.removeThis(this);
        }

        txtEmail.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_SEND) {

                initModel();
                return@OnEditorActionListener true
            }

            false

        })

    }

    //ESTADO

    fun initEstado() {

        val valores = ArrayList<String>()
        valores.add("AC")
        valores.add("AL")
        valores.add("AM")
        valores.add("AP")
        valores.add("BA")
        valores.add("CE")
        valores.add("DF")
        valores.add("ES")
        valores.add("GO")
        valores.add("MA")
        valores.add("MG")
        valores.add("MS")
        valores.add("MT")
        valores.add("PA")
        valores.add("PB")
        valores.add("PE")
        valores.add("PI")
        valores.add("PR")
        valores.add("RJ")
        valores.add("RN")
        valores.add("RO")
        valores.add("RR")
        valores.add("RS")
        valores.add("SC")
        valores.add("SE")
        valores.add("SP")
        valores.add("TO")

        Dialogs.addListA(this, "Estados", valores, object : Dialogs.CALL {

            override fun call(value: String?, p: Int) {

                txtEstado.setText(value);

            }

        });


    }

    //CEP

    fun initCep() {


        txtCep.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (txtCep.text.toString().count() == 9) {

                    val cep = txtCep.text.toString().replace("-", "");

                    //"https://viacep.com.br/ws/$cep/json/"
                    //txtCidade.setText(json!!.getString("localidade"));
                    //txtEstado.setText(json!!.getString("uf"));
                    //txtEndereco.setText(json!!.getString("logradouro"));
                    //txtBairro.setText(json!!.getString("bairro"));




                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


    }



    //VALIDATION


    fun isValidation(): Boolean {

        if (txtNome.text.toString().count() == 0) {
            Dialogs.add(this, "Preencha o campo nome.");
            return false;
        }

        if (txtEmail.text.toString().count() == 0) {
            Dialogs.add(this, "Preencha o campo e-mail.");
            return false;
        }

        if (!FormValidation.checkEmail(txtEmail.text.toString())) {
            Dialogs.add(this, "E-mail inválido.");
            return false;
        }

        if (txtCpf.text.toString().count() == 0) {
            Dialogs.add(this, "CPF inválido.");
            return false;
        }


        if (txtCpf.text.toString().count() != 14) {
            Dialogs.add(this, "CPF inválido.");
            return false;
        }

        if (txtSenha.text.toString().count() == 0) {
            Dialogs.add(this, "Preencha o campo senha.");
            return false;
        }

        if (txtSenha.text.toString().count() < 6) {
            Dialogs.add(this, "Senha com no mínimo 6 caracteres.");
            return false;
        }

        if (!txtSenha.text.toString().equals(txtSenhaC.text.toString())) {
            Dialogs.add(this, "Senha diferentes.");
            return false;
        }

        if (txtTelefone.text.toString().count() == 0) {
            Dialogs.add(this, "Telefone inválido.");
            return false;
        }

        if (txtTelefone.text.toString().count() != 14) {
            Dialogs.add(this, "Telefone inválido.");
            return false;
        }


        if (txtCep.text.toString().count() != 9) {

            Dialogs.add(this, "Preencha o campo cep.");

            return false;
        }

        if (!FormValidation.checkCep(txtCep.text.toString())) {
            Dialogs.add(this, "CEP inválido.");
            return false;
        }

        if (txtEstado.text.toString().count() == 0) {

            Dialogs.add(this, "Preencha o campo estado.");

            return false;
        }

        if (txtCidade.text.toString().count() == 0) {

            Dialogs.add(this, "Preencha o campo cidade.");

            return false;
        }

        if (txtBairro.text.toString().count() == 0) {

            Dialogs.add(this, "Preencha o campo bairro.");

            return false;
        }


        if (txtEndereco.text.toString().count() == 0) {

            Dialogs.add(this, "Preencha o campo endereço.");

            return false;
        }

        if (txtNumero.text.toString().count() == 0) {

            Dialogs.add(this, "Preencha o campo número.");

            return false;
        }

        return true;
    }

    //MODEL

    fun initModel() {

        Teclado.removeThis(this);

        if (!isValidation())
            return;

        btn.isEnabled = false;






    }

    //PHOTO

    fun onUploadPhoto()
    {

        val valores = ArrayList<String>()
        valores.add("Carregar foto galeria");
        valores.add("Tirar foto")

        Dialogs.addListA(this, "Foto", valores, object : Dialogs.CALL {

            override fun call(value: String?, p: Int) {

                if(p == 0 )
                {
                    initGaleria();
                }else{

                    initVideo();

                }

            }

        });



    }


    fun initVideo()
    {

        initVideoUploadPhoto({ file: File, uri: Uri, s: String ->

            cadastroFoto.setImageURI(uri!!);


        },{erro:String->


        })


    }


    fun initGaleria()
    {
        initGaleriaUploadPhoto({ file: File, uri: Uri, s: String ->

            cadastroFoto.setImageURI(uri!!);

        },{erro:String->


        })

    }






}//END CLASS
