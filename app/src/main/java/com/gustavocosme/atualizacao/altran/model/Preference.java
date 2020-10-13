package com.gustavocosme.atualizacao.altran.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.gustavocosme.atualizacao.altran.model.AsyncTaskCustom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@SuppressWarnings("unchecked")
public class Preference {

	public static String TAG = "Model";
	public Context context;
	public AsyncTaskCustom TASK;
	public SharedPreferences sharedPreferences;

	public Preference(Context context) {

		this.context = context;
		TASK = new AsyncTaskCustom();
		sharedPreferences = getSharedPreference();



	}

	// ###########################################################################//
	// #REQUESTS
	// ###########################################################################//




	// ###########################################################################//
	// #PREFERENCES
	// ###########################################################################//

	public SharedPreferences getSharedPreference() {
		return context.getSharedPreferences(context.getPackageName(),
				Context.MODE_PRIVATE);
	}

	/**
	 * @param value
	 *            VER SER EXISTE A REFERENCIA NO SHARED PREFERENCE
	 * @return
	 */
	public boolean isValidationPreferences(String value) {

		try {

			String load = sharedPreferences.getString(value,
					"defaultStringIfNothingFound");

			if (load.equals("defaultStringIfNothingFound"))
				return false;

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param list
	 * @param id
	 * @return
	 * 
	 *         VER SE EXISTE UM ITEM NA LISTA _ USADO EM FAVORITOS
	 * 
	 */
	public Boolean isValidationSimpleList(LinkedList<String> list, String id) {
		if (list.contains(id))
			return true;
		else
			return false;
	}

	/**
	 * @param idName
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * 
	 *             RETORNA UMA A STRING EM SHPR SE TIVER - SE NÃO "";
	 */
	public String getStringPreferences(final String idName)
			throws InterruptedException, ExecutionException {

		TASK = new AsyncTaskCustom();

		TASK.setCall(new AsyncTaskCustom.Call() {

			@Override
			public Object onRun() {

				try {

					String load = sharedPreferences.getString(idName,
							"defaultStringIfNothingFound");

					if (load.equals("defaultStringIfNothingFound"))
						return "";

					return load;

				} catch (Exception e) {
					return "";
				}

			}

			@Override
			public void onComplete(Object result) {

			}

		});

		return TASK.execute().get().toString();
	}

	/**
	 * @param idName
	 * @param value
	 * @return
	 * 
	 *         ADICIONA UMA STRING EM SHPR - TRUE DE CADASTRAR - FALSE SE NÃO
	 * 
	 */
	public boolean addCommitPreferences(String idName, String value) {

		try {
			sharedPreferences.edit().putString(idName, value).commit();

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean deleteCommitPreferences(String idName) {
		
		try {
			sharedPreferences.edit().remove(idName).commit();
			
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * @param value
	 * @return
	 * 
	 *         RETORNA UMA LISTA SIMPLES - USADO EM FAVORITOS
	 */
	public LinkedList<String> getSimpleListPreferences(String value) {

		try {
			String bookmarks_string = sharedPreferences.getString(value, "");
			return new LinkedList<String>(Arrays.asList(bookmarks_string
					.split(",")));
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * @param idName
	 * @param list
	 * @param value
	 * 
	 *            ADICIONA UM ITEM EM UMA LISTA SIMPLES USADO EM FAVORITOS
	 * 
	 */
	public void addStringSimpleList(String idName, LinkedList<String> list,
                                    String value) {

		list.add(value);

		StringBuilder builder = new StringBuilder();
		Integer i = 0;
		for (String bookmark : list) {
			if (i != 0)
				builder.append(",");
			builder.append(bookmark);
			i++;
		}
		String result = builder.toString();

		sharedPreferences.edit().putString(idName, result).commit();
	}

	/**
	 * @param idName
	 * @param list
	 * @param value
	 * 
	 *            REMOVE UM ITEM NA LISTA SIMPLES - USADO EM FAVORITOS
	 */
	public void removeStringSimpleList(String idName, LinkedList<String> list,
                                       String value) {

		list.add(value);

		StringBuilder builder = new StringBuilder();
		Integer i = 0;
		for (String bookmark : list) {
			if (i != 0)
				builder.append(",");
			builder.append(bookmark);
			i++;
		}
		String result = builder.toString();

		sharedPreferences.edit().putString(idName, result).commit();

	}

	// #############################################################################//
	// JSON UTILS
	// #############################################################################//

	/**
	 * @param jsonArrayList
	 * @return
	 * 
	 *         Converte uma JsonArray.toString em um ArrayList<ModelData>
	 * 
	 *         try {
	 * 
	 * 
	 * 
	 *         Type listType = new TypeToken<List<MainData>>() { }.getType();
	 *         List<MainData> dados = (List<MainData>) model
	 *         .parseJsonArrayToClass(JSON.getJSONArray("events") .toString(),
	 *         listType); Log.e("Dados: ", dados.get(0).getTitle());
	 * 
	 *         } catch (JSONException e) {
	 * 
	 *         Log.e("ERRO", e.getMessage());
	 * 
	 *         }
	 * 
	 */


	/**
	 * @param value
	 * @param call
	 * 
	 *            SPEED LOOP
	 * 
	 *            JSON = (JSONObject) value;
	 * 
	 *            try{
	 * 
	 *            Type listType = new TypeToken<List<MainData>>() {}.getType();
	 *            String ArrayListString =
	 *            JSON.getJSONArray("events").toString(); List<MainData> dados =
	 *            model.parseJsonArrayToClass(ArrayListString, listType);
	 *            Log.e("Dados Size: ", String.valueOf(dados.size()));
	 * 
	 *            model.SearchList(dados,new Model.CallLoop() {
	 * @Override public void run(Object value) {
	 * 
	 *           MainData data = (MainData) value;
	 *           Log.e("Run Title: ",data.getTitle());
	 * 
	 *           }
	 * @Override public void onComplete() {
	 * 
	 * 
	 *           Log.e("onCompleteRUN: ","on Complete Run");
	 * 
	 * 
	 *           } });
	 * 
	 *           } catch (JSONException e) {
	 * 
	 *           Log.e("ERRO", e.getMessage());
	 * 
	 *           }
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public <T> void SearchList(List<T> value, CallLoop call) {

		Iterator i = value.iterator();

		while (i.hasNext()) {

			call.run(i.next());
		}

		call.onComplete();

	}

	// #############################################################################//
	// #IMAGES
	// #############################################################################//



	// #############################################################################//
	// GOOGLE ANALYTICS
	// #############################################################################//


	// #############################################################################//
	// #CALL
	// #############################################################################//

	/**
	 * @author gustavocosme
	 *
	 */
	public interface CallModel {

		/**
		 * @param value
		 *            SE CARREGAR CORRETAMENTE
		 */
		public void onCompleteOnline(Object value);

		/**
		 * @param value
		 *            SE CARREGOU CORRETAMENTE E TIVER OFFLINE
		 */
		public void onCompleteOffline(Object value);

		/**
		 * ERRO NO CARREGAMENTO
		 */
		public void onErro(String value);
	}

	public interface CallLoop {
		public void run(Object value);

		public void onComplete();
	}

}// END CLASS
