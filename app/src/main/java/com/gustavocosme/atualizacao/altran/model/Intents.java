package com.gustavocosme.atualizacao.altran.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.text.Normalizer;

public class Intents {
	
	public static void email(Context context, String to, String subject, String body)
	{
	    StringBuilder builder = new StringBuilder("mailto:" + Uri.encode(to));
	    if (subject != null) {
	    	
	    	
	    	String myStringSub =  Normalizer.normalize(Uri.encode(subject), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	    	String myStringBoby =  Normalizer.normalize(Uri.encode(body), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

	        builder.append("?subject=" + myStringSub);
            builder.append("&body=" + body);

	    }
	    
	    String uri = builder.toString();
	    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
	    context.startActivity(intent);
	}	

}
