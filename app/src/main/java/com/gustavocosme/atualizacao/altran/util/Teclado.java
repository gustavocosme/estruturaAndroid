package com.gustavocosme.atualizacao.altran.util;


import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Teclado {

	public static void removeThis(Activity ac)
	{
		
		try {
			
			View view = ac.getCurrentFocus();
			if (view != null) {  
			    InputMethodManager imm = (InputMethodManager)ac.getSystemService(Context.INPUT_METHOD_SERVICE);
			    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		
		
	}
	
	
	
	public static void hideSoftKeyboard(Context context, IBinder i) {
		
		try {
			
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(i,0); 
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	
			
	}

	public static void showSoftKeyboard(Context context, IBinder i) {
		
		try {
			
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInputFromWindow(i, 0, 0); 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}


	
	public static void showSoftKeyboard(Activity context, View view) {
		
		try {
			
		    @SuppressWarnings("static-access")
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
		    view.requestFocus();
		    inputMethodManager.showSoftInput(view, 0);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
}//END CLAS
