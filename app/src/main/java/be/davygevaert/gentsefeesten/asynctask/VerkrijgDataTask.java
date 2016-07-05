package be.davygevaert.gentsefeesten.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.davygevaert.gentsefeesten.databank.DataDB;
import be.davygevaert.gentsefeesten.model.Data;
import be.davygevaert.gentsefeesten.tools.JSON_Tools;

/**
 * Created by Davy on 14/06/2016.
 */
public class VerkrijgDataTask extends AsyncTask<Object, Integer, DataDB> {

    private static String TAG = "VerkrijgDataTask";

    private DataDB dataDB;
    private Context context;

    public VerkrijgDataTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected DataDB doInBackground(Object... objects) {
        dataDB = new DataDB(context);

        try {
            // eerste object casten naar een String waarde
            String fileName = (String) objects[0];
            //Log.i(TAG, "fileName inhoud : " + fileName);

            JSONArray jsonArray = new JSONArray(JSON_Tools.loadJSONFromAsset(fileName, context));
            //Log.i(TAG, "array inhoud : " + jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                // huidig jsonObject in jsonArray
                JSONObject json_obj = jsonArray.getJSONObject(i);

                // initialisatie en aanmaken nieuw Data object
                Data data = new Data();
                data.setTimestamp(json_obj.getString("timestamp"));
                data.setDay(json_obj.getString("day"));

                // toevoegen object data aan database DataDB
                dataDB.addData(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataDB;
    }

    @Override
    protected void onPostExecute(DataDB dataDB) {
        super.onPostExecute(dataDB);
    }
}
