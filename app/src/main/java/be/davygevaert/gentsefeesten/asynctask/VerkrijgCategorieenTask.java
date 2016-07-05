package be.davygevaert.gentsefeesten.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.davygevaert.gentsefeesten.databank.CategorieDB;
import be.davygevaert.gentsefeesten.model.Categorie;
import be.davygevaert.gentsefeesten.tools.JSON_Tools;

/**
 * Created by Davy on 14/06/2016.
 */
public class VerkrijgCategorieenTask extends AsyncTask<Object, Integer, CategorieDB> {

    private static String TAG = "VerkrijgCategorieenTask";

    private CategorieDB categorieDB;
    private Context context;

    public VerkrijgCategorieenTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected CategorieDB doInBackground(Object... objects) {
        categorieDB = new CategorieDB(context);

        try {
            // eerste object casten naar een String waarde
            String fileName = (String) objects[0];
            //Log.i(TAG, "fileName inhoud : " + fileName);

            JSONArray jsonArray = new JSONArray(JSON_Tools.loadJSONFromAsset(fileName, context));
            //Log.i(TAG, "array inhoud : " + jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                // huidig jsonObject in jsonArray
                JSONObject json_obj = jsonArray.getJSONObject(i);

                // initialisatie en aanmaken nieuw Categorie object
                Categorie categorie = new Categorie();
                categorie.setCategorieID(json_obj.getString("id"));
                categorie.setCategorieTitel(json_obj.getString("title"));

                // toevoegen object categorie aan database CategorieDB
                categorieDB.addCategorie(categorie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categorieDB;
    }

    @Override
    protected void onPostExecute(CategorieDB categorieDB) {
        super.onPostExecute(categorieDB);
    }
}
