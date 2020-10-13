package com.gustavocosme.atualizacao.presentation.home


import android.os.Bundle
import android.widget.TextView
import com.gustavocosme.atualizacao.R
import com.gustavocosme.atualizacao.presentation.main.Main
import gustavocosme.ui.BaseFragment

class Home : BaseFragment() {

    init {

        title = "Home";
        setLayout(R.layout.home);
    }

    override fun init() {

        cachedView!!.findViewById<TextView>(R.id.btn).setOnClickListener {

            val l = HomeInterna.newInstance(0)
            Main.INSTANCE.nav(l);

        }




    }




    companion object {

        fun newInstance(instance: Int): Home {
            val args = Bundle()
            args.putInt(ARGS_INSTANCE, instance)
            val fragment = Home()
            fragment.setArguments(args)
            return fragment
        }

    }


}//END CLASS
