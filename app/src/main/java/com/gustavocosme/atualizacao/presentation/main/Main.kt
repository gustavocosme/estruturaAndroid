package com.gustavocosme.atualizacao.presentation.main

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gustavocosme.atualizacao.R
import com.gustavocosme.atualizacao.presentation.home.Home
import com.gustavocosme.atualizacao.presentation.list.ListR
import com.gustavocosme.atualizacao.altran.util.Teclado
import com.ncapdevi.fragnav.FragNavController
import gustavocosme.ui.BaseFragment
import kotlinx.android.synthetic.main.main.*


//######################################################
//MENU
//SAVE
//BACK
//FRAGMENTS
//TOPO
//######################################################

class Main() : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener, FragNavController.RootFragmentListener, FragNavController.TransactionListener  {

    val INDEX_1 = FragNavController.TAB1;
    val INDEX_2 = FragNavController.TAB2;

    lateinit var mTitle: TextView;
    var fragNavController: FragNavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.main);

        if(savedInstanceState != null)
        {
            return;
        }

        initTopo();
        initFragment(savedInstanceState)
        initBottomNavigation();
        initStatusBar();

        Main.INSTANCE = this;

    }

    //######################################################
    //STATUS BAR
    //######################################################

    fun initStatusBar()
    {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }


        } catch (e: java.lang.Exception) {

        }


    }


    //######################################################
    //STATUS BAR
    //######################################################

    //######################################################
    //MENU
    //######################################################

    private fun initBottomNavigation() {

        bottomBar.setOnNavigationItemSelectedListener( this )
        bottomBar.setOnNavigationItemReselectedListener( this )

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.m1)
        {
            fragNavController!!.switchTab(INDEX_1);

        }

        if(item.itemId == R.id.m2)
        {

            fragNavController!!.switchTab(INDEX_2);

        }



        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {

        fragNavController!!.clearStack()

    }

    //######################################################
    //MENU
    //######################################################

    //######################################################
    //SAVE
    //######################################################

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState!!)
        if (fragNavController!! != null) {
            fragNavController!!.onSaveInstanceState(outState!!)
        }

    }

    //######################################################
    //SAVE
    //######################################################


    //######################################################
    //BACK
    //######################################################

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {

                Teclado.removeThis(this);
                fragNavController!!.popFragment()

            }
        }

        return true

    }



    override fun onBackPressed() {


        updateTitle(mTitle.text.toString())
        Teclado.removeThis(this);

        if (!fragNavController!!.popFragment()) {

            val startMain = Intent(Intent.ACTION_MAIN)
            startMain.addCategory(Intent.CATEGORY_HOME)
            startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startMain)
        }


    }



    //######################################################
    //BACK
    //######################################################

    //######################################################
    //FRAGMENTS
    //######################################################

    fun initFragment(savedInstanceState: Bundle?){


        fragNavController = FragNavController.newBuilder(
            savedInstanceState,
            supportFragmentManager,
            R.id.container
        )
            .switchController { i, fragNavTransactionOptions ->

                if(i == 0)
                {
                    bottomBar.setSelectedItemId(R.id.m1);
                }

                if(i == 1)
                {
                    bottomBar.setSelectedItemId(R.id.m2);
                }


            }
            .popStrategy(1)
            .transactionListener(this)
            .rootFragmentListener(this, 3)
            .build()

        fragNavController!!.executePendingTransactions()



    }

    override fun getRootFragment(index: Int): Fragment {

        when (index) {
            INDEX_1 -> return Home.newInstance(0)
            INDEX_2 -> return ListR.newInstance(0)
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {

        val base = fragment as BaseFragment?
        updateTitle(base!!.title)


        if (supportActionBar != null && fragNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!fragNavController!!.isRootFragment)
        }



    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {

        val base = fragment as BaseFragment
         updateTitle(base.title)


        if (supportActionBar != null && fragNavController != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(!fragNavController!!.isRootFragment)
        }
    }

    fun onPop() {

        Teclado.removeThis(this);
        fragNavController!!.clearStack()
    }

    fun nav(f:Fragment) {

        fragNavController!!.pushFragment(f);

    }




    //######################################################
    //FRAGMENTS
    //######################################################


    //######################################################
    //TOPO
    //######################################################

    fun initTopo()
    {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar);
        updateTitle("Home");

        try {

            toolbar.navigationIcon!!.setColorFilter(
                resources.getColor(R.color.black),
                PorterDuff.Mode.SRC_ATOP
            )

        } catch (e: Exception) {

        }

        try {

            val upArrow = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
            upArrow.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_ATOP)
            supportActionBar!!.setHomeAsUpIndicator(upArrow);

        } catch (e: Exception) {

        }



    }

    fun updateTitle(title: String) {

        mTitle.text = title;
        Log.e("title",title);

        /*

        if(title.equals("Eventos") || title.equals("Buscar") || title.equals("Favoritos") || title.equals("Sobre") || title.equals("Contato"))
        {

            //mLogo.visibility = View.VISIBLE;
            mTitle.visibility = View.GONE;

        }else{

            //mLogo.visibility = View.GONE;
            mTitle.visibility = View.VISIBLE;

        }

        */



    }

    //######################################################
    //TOPO
    //######################################################

    companion object
    {
        lateinit var INSTANCE:Main;

    }


}//END CLASS
