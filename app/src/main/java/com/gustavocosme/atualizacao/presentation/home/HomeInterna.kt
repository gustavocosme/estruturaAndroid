package com.gustavocosme.atualizacao.presentation.home


import android.os.Bundle
import com.gustavocosme.atualizacao.R
import gustavocosme.ui.BaseFragment


class HomeInterna : BaseFragment() {


    init {

        title = "Home interna";
        setLayout(R.layout.home_interna);
    }

    override fun init() {





    }




    companion object {

        fun newInstance(instance: Int): HomeInterna {
            val args = Bundle()
            args.putInt(ARGS_INSTANCE, instance)
            val fragment = HomeInterna()
            fragment.setArguments(args)
            return fragment
        }

    }


}//END CLASS
