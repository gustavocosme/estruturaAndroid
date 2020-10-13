package com.gustavocosme.atualizacao.altran.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gustavocosme.atualizacao.R;
import com.gustavocosme.atualizacao.altran.util.Teclado;


/**
 * Created by gustavocosme on 31/01/18.
 */

public class Ac extends AppCompatActivity {


    TextView mTitle;



    public void initTopo(String titulo)
    {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        //Fonts.addFontBold(this, mTitle);
        mTitle.setText(titulo);

        try {

            Drawable backArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
            backArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);

        }catch (Exception e)
        {


        }



    }



    public void  setTitle(String title)
    {
        mTitle.setText(title);
    }

    //******************************************************//
    //MENU
    //******************************************************//

    @Override
    public boolean onSupportNavigateUp() {

        Teclado.removeThis(this);
        onBackPressed();
        return true;
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

        // overridePendingTransition(R.interpolator.left_to_right_in,
        //       R.interpolator.left_to_right_out);
    }


    //******************************************************//
    //MENU
    //******************************************************//

}
