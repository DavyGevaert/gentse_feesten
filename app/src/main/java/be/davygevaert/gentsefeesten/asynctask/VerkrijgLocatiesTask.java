package be.davygevaert.gentsefeesten.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.davygevaert.gentsefeesten.databank.LocatieDB;
import be.davygevaert.gentsefeesten.model.Locatie;
import be.davygevaert.gentsefeesten.tools.JSON_Tools;

/**
 * Created by Davy on 14/06/2016.
 */
public class VerkrijgLocatiesTask extends AsyncTask<Object, Integer, LocatieDB> {

    private static String TAG = VerkrijgLocatiesTask.class.getSimpleName();

    private LocatieDB locatieDB;
    private Context context;

    public VerkrijgLocatiesTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected LocatieDB doInBackground(Object... objects) {
        locatieDB = new LocatieDB(context);

        try {
            // eerste object casten naar een String waarde
            String fileName = (String) objects[0];
            //Log.i(TAG, "fileName inhoud : " + fileName);

            JSONArray jsonArray = new JSONArray(JSON_Tools.loadJSONFromAsset(fileName, context));
            //Log.i(TAG, "array inhoud : " + jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                // huidig jsonObject in jsonArray
                JSONObject json_obj = jsonArray.getJSONObject(i);

                // initialisatie en aanmaken nieuw Locatie object
                Locatie locatie = new Locatie();
                locatie.setLocatieID(json_obj.getString("id"));
                locatie.setLocatieNaam(json_obj.getString("naam"));

                // toevoegen object locatie aan database LocatieDB
                locatieDB.addLocatie(locatie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return locatieDB;
    }

    @Override
    protected void onPostExecute(LocatieDB categorieDB) {
        super.onPostExecute(categorieDB);
    }
}