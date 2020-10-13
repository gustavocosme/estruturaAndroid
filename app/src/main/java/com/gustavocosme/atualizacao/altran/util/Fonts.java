package com.gustavocosme.atualizacao.altran.util;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

public class Fonts
{


    public static void addFontBold(Context context, TextView campo)
    {
        try {

            Typeface myTypeface 		= Typeface.createFromAsset(context.getAssets(),"fonts/b.otf");
            campo.setTypeface(myTypeface, Typeface.BOLD);

        }
        catch (Exception e)
        {
            Log.e("ERRO FONT BOLD",e.getMessage());

        }
    }

    public static void addFontRegular(Context context, TextView campo)
    {
        try {

            Typeface myTypeface 		= Typeface.createFromAsset(context.getAssets(),"fonts/r.ttf");
            campo.setTypeface(myTypeface, Typeface.NORMAL);
        }
        catch (Exception e)
        {
            Log.e("ERRO FONT REGULAR",e.getMessage());
        }

    }



}//END CLASS
