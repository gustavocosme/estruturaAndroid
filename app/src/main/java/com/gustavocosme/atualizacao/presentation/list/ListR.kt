package com.gustavocosme.atualizacao.presentation.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gustavocosme.atualizacao.R
import com.gustavocosme.atualizacao.model.viewModel.ListViewModel
import gustavocosme.ui.BaseFragment


class ListR : BaseFragment() {


    lateinit var list:RecyclerView;
    lateinit var swipe:SwipeRefreshLayout;

    init {

        title = "List";
        setLayout(R.layout.list);

    }

    override fun init() {

        list = cachedView!!.findViewById(R.id.list) as RecyclerView;
        swipe = cachedView!!.findViewById(R.id.swipe) as SwipeRefreshLayout;

        initViewModel();

    }


    private fun initViewModel()
    {

        kotlin.run {

            swipe.isRefreshing = true;

        }

        val model: ListViewModel = ViewModelProviders.of(this).get(
            ListViewModel::class.java);

        model.dadosLibeData.observe(this, Observer {

            it?.let { dados ->

                with(list){

                    layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
                    setHasFixedSize(true)
                    adapter = ListAdp(dados)

                }

                swipe.isRefreshing = false;

            }

        });


        swipe.setOnRefreshListener {

            model.getDados();

        }

        model.getDados();


    }


    companion object {

        fun newInstance(instance: Int): ListR {
            val args = Bundle()
            args.putInt(ARGS_INSTANCE, instance)
            val fragment = ListR()
            fragment.setArguments(args)
            return fragment
        }

    }


}//END CLASS
