package com.gustavocosme.atualizacao.altran.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.gustavocosme.atualizacao.R


class FormEditText(context:Context, attrs: AttributeSet): RelativeLayout(context,attrs) {

    lateinit var cachedView: View;
    var title = "";
    var isValidationText = true;
    lateinit var txt:TextView
    lateinit var btn:Button
    lateinit var img:ImageView

    init {

        val attributes          = context.obtainStyledAttributes(attrs, R.styleable.FormEditText);
        title                   = attributes!!.getString(R.styleable.FormEditText_title)!!
        isValidationText        = attributes!!.getBoolean(R.styleable.FormEditText_isValidationText,true)!!
        attributes.recycle()

        inflate();

    }

    private fun inflate() {

        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        cachedView = mInflater.inflate(R.layout.form_field, this, true)

        inicialize();

    }

    fun inicialize() {

        txt = cachedView.findViewById(R.id.txt) as TextView;
        btn = cachedView.findViewById(R.id.btn) as Button;
        img = cachedView.findViewById(R.id.img) as ImageView;
        img.visibility = View.GONE;
        txt.setText(title);

    }











}//END CLASS