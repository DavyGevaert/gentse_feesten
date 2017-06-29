package be.davygevaert.gentsefeesten.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.davygevaert.gentsefeesten.databank.OrganisatorDB;
import be.davygevaert.gentsefeesten.model.Organisator;
import be.davygevaert.gentsefeesten.tools.JSON_Tools;

/**
 * Created by Davy on 12/06/2017.
 */
public class VerkrijgOrganisatorenTask extends AsyncTask<Object, Integer, OrganisatorDB> {

    private static String TAG = VerkrijgOrganisatorenTask.class.getSimpleName();

    private OrganisatorDB organisatorDB;
    private Context context;

    public VerkrijgOrganisatorenTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected OrganisatorDB doInBackground(Object... objects) {
        organisatorDB = new OrganisatorDB(context);

        try {
            // eerste object casten naar een String waarde
            String fileName = (String) objects[0];
            //Log.i(TAG, "fileName inhoud : " + fileName);

            JSONArray jsonArray = new JSONArray(JSON_Tools.loadJSONFromAsset(fileName, context));
            Log.i(TAG, "array inhoud : " + jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {

                // Log.i(TAG, "lengte jsonArray : " + jsonArray.length());

                // huidig jsonObject in jsonArray
                JSONObject json_obj = jsonArray.getJSONObject(i);

                String id = json_obj.getString("@id");
                // Log.i(TAG, "@id : " + id);

                JSONObject naam = json_obj.getJSONObject("name");
                // Log.i(TAG, "naam : " + naam.toString());

                String nl_benaming = naam.getString("nl");
                // Log.i(TAG, "nl_benaming : " + nl_benaming);

                JSONObject adres = json_obj.getJSONObject("address");
                // Log.i(TAG, "adres : " + adres.toString());

                String straat = adres.getString("streetAddress");
                String postcode = adres.getString("postalCode");
                String gemeente = adres.getString("addressLocality");

                // initialisatie en aanmaken nieuw Organisator object
                Organisator organisator = new Organisator();
                organisator.setOrganisatieId(json_obj.getString("@id"));
                organisator.setOrganisatieNaam(naam.getString("nl"));
                organisator.setOrganisatieStraat(adres.getString("streetAddress"));
                organisator.setOrganisatiePostcode(adres.getString("postalCode"));
                organisator.setOrganisatieGemeente(adres.getString("addressLocality"));

                // toevoegen object organisatie aan database OrganisatorDB
                organisatorDB.addDOrganisator(organisator);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return organisatorDB;
    }

    @Override
    protected void onPostExecute(OrganisatorDB organisatorDB) {
        super.onPostExecute(organisatorDB);
    }
}