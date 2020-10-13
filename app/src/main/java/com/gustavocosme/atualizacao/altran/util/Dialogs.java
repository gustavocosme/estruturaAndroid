package com.gustavocosme.atualizacao.altran.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class Dialogs {

	public interface CALL {

		public void call(String value, int position);

	}

	public interface CALLP {
		
		public void call(String value, int p);
		
	}

	public static void showDate(Context context, String titulo, final CALL call,
                                DialogInterface.OnDismissListener onCancel) {

		Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

				int mYear = year;
				int mMonth = monthOfYear + 1;
				int mDay = dayOfMonth;

				String aux = "";
				String aux2 = "";

				if (mDay < 10)
					aux = "0";

				if (mMonth < 10)
					aux2 = "0";

				call.call(aux + new StringBuilder().append(mDay).append("/").append(aux2 + String.valueOf(mMonth)).append("/").append(mYear).append(" ").toString(),0);

			}

		}, mYear, mMonth, mDay);

		dialog.setTitle(titulo);
		dialog.setCancelable(false);

		dialog.setOnDismissListener(onCancel);

		//DatePicker datePicker = dialog.getDatePicker();
		//datePicker.setMaxDate(c.getTimeInMillis());

		dialog.show();

	}

	public static void showTime(Context context, final CALL call) {
		int mHour, mMinute;

		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);

		TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				call.call(String.valueOf(hourOfDay + ":" + minute),0);

			}
		}, mHour, mMinute, false);

		tpd.show();

	}

	public static void addListA(Context context, String title, final ArrayList<String> dados, final CALL call) {

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();


			}
		});


		ListView lv = new ListView(alertDialog.getContext());
		alertDialog.setView(lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, dados);
		lv.setAdapter(adapter);

		final AlertDialog create = alertDialog.create();
		create.setTitle(title);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				call.call(dados.get(position),position);

				create.dismiss();

			}

		});

		create.show();

	}

	public static void addList(Context context, String title, final String[] dados, final CALL call) {
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		ListView lv = new ListView(alertDialog.getContext());
		alertDialog.setView(lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, dados);
		lv.setAdapter(adapter);

		final AlertDialog create = alertDialog.create();
		create.setTitle("PREMIUM CONSTRUTORA");
		create.setMessage(title);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				call.call(dados[position],position);

				create.dismiss();

			}

		});

		create.show();

	}

	public static void addListPosition(Context context, String title, final String[] dados, final CALLP call) {
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		ListView lv = new ListView(alertDialog.getContext());
		alertDialog.setView(lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, dados);
		lv.setAdapter(adapter);
		
		final AlertDialog create = alertDialog.create();
		create.setTitle(title);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				call.call(dados[position],position);
				
				create.dismiss();
				
			}
			
		});
		
		create.show();
		
	}

	public interface CallSelect {

		public void onSelect(int position);

		public void onCancel();

	}

	public static void addListAdapter(Context context, String title, BaseAdapter adapter, final CallSelect call) {

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		ListView lv = new ListView(alertDialog.getContext());
		alertDialog.setView(lv);

		alertDialog.setTitle(title);

		lv.setAdapter(adapter);

		alertDialog.setCancelable(false);

		alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

				call.onCancel();

			}
		});

		final AlertDialog create = alertDialog.create();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				create.dismiss();

				call.onSelect(position);

			}

		});

		create.show();

	}

	public static void addYesNo(Context context, String message, final CALL call) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(context);

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				switch (which) {

				case DialogInterface.BUTTON_POSITIVE:

					call.call("1",1);

					break;

				case DialogInterface.BUTTON_NEGATIVE:

					call.call("2",2);
					dialog.dismiss();

					break;

				}
			}
		};

		builder.setMessage(message).setPositiveButton("Sim", dialogClickListener).setNegativeButton("Cancelar",
				dialogClickListener);

		builder.show();

	}

	public static void add(Context context, String message) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

			}
		};

		builder.setMessage(message).setPositiveButton("OK", dialogClickListener);

		builder.show();

	}

}// END CLASS
