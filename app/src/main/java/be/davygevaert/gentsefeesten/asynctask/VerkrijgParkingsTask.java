package be.davygevaert.gentsefeesten.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import be.davygevaert.gentsefeesten.exception.DownloadException;
import be.davygevaert.gentsefeesten.interfaces.AsyncResponse;
import be.davygevaert.gentsefeesten.model.City;
import be.davygevaert.gentsefeesten.model.Parking;
import be.davygevaert.gentsefeesten.model.Server;

/**
 * Created by Davy on 29/06/2016.
 */

public class VerkrijgParkingsTask extends AsyncTask<Object, Integer, ArrayList<Parking>> {

    private static String TAG = VerkrijgParkingsTask.class.getSimpleName();

    public AsyncResponse delegate = null;   //Call back interface

    public VerkrijgParkingsTask(AsyncResponse asyncResponse) {
        delegate = asyncResponse;           //Assigning call back interfacethrough constructor
    }

    @Override
    protected ArrayList<Parking> doInBackground(Object... objects) {
        String url = (String) objects[0];

        ArrayList<Parking> results = null;

        try {
            results = downloadData(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    protected void onPostExecute(ArrayList<Parking> parkingArrayList) {

        // for logging purposes
        for (int i = 0; i < parkingArrayList.size(); i++) {
            /*
            Log.i(TAG, "naam parking : " + parkingArrayList.get(i).getName() +
                    ", adres: " + parkingArrayList.get(i).getAddress() + " " + parkingArrayList.get(i).getCity().getName());
            */
        }

        super.onPostExecute(parkingArrayList);

        delegate.processFinish(parkingArrayList);
    }

    private ArrayList<Parking> downloadData(String requestUrl) throws IOException, DownloadException {
        InputStream inputStream = null;

        HttpURLConnection urlConnection = null;

        /* forming the java.net.URL object */
        URL url = new URL(requestUrl);

        /* casting HttpUrlConnection */
        urlConnection = (HttpURLConnection) url.openConnection();

        /* optional request header */
        urlConnection.setRequestProperty("Content-Type", "application/json");

        /* option request header */
        urlConnection.setRequestProperty("Accept", "application/json");

        /* for Get request */
        urlConnection.setRequestMethod("GET");

        int statusCode = urlConnection.getResponseCode();

        /* 200 represents HTTP OK */
        if (statusCode == 200) {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());

            String response = convertInputStream(inputStream);
            ArrayList<Parking> parkingData = parseResult(response);

            return parkingData;
        } else {
            throw new DownloadException("Failed to fetch data!");
        }
    }

    private String convertInputStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        /* Close stream */
        if (null != inputStream) {
            inputStream.close();
        }

        return result;
    }

    private ArrayList<Parking> parseResult(String result) {
        Log.i(TAG, "result : " + result);
        ArrayList<Parking> arrayList = null;

        try {
            JSONArray parkings = new JSONArray(result);
            Log.i(TAG, "aantal parkings : " + parkings.length());

            arrayList = new ArrayList<Parking>();        // results niet juist ==> 2 PLAATSEN IN SINT-NIKLAAS !!!

            for (int i = 0; i < parkings.length(); i++) {

                Parking p = new Parking();
                JSONObject parking = parkings.getJSONObject(i);

                p.setId(Long.parseLong(parking.getString("id")));
                p.setLastModifiedDate(parking.getString("lastModifiedDate"));
                p.setName(parking.getString("name"));
                p.setDescription(parking.getString("description"));
                p.setLatitude(parking.getString("latitude"));
                p.setLongitude(parking.getString("longitude"));
                p.setAddress(parking.getString("address"));
                p.setContactInfo(parking.getString("contactInfo"));
                // p.setBlurAvailability(parking.getString("blurAvailability"));

                // JSONObject opvragen van City
                JSONObject stad = parking.getJSONObject("city");

                City c = new City();
                c.setId(Long.parseLong(stad.getString("id")));
                c.setName(stad.getString("name"));
                // Log.i(TAG, "Naam stad : " + c.getName());
                p.setCity(c);

                // JSONObject opvragen van Server
                JSONObject server = parking.getJSONObject("parkingServer");

                Server s = new Server();
                s.setId(Long.parseLong(server.getString("id")));
                s.setName(server.getString("name"));
                // Log.i(TAG, "Naam server : " + s.getName());
                p.setServer(s);

                p.setSuggestedFreeThreshold(Integer.parseInt(parking.getString("suggestedFreeThreshold")));
                p.setSuggestedFullThreshold(Integer.parseInt(parking.getString("suggestedFullThreshold")));
                p.setCapacityRounding(Integer.parseInt(parking.getString("capacityRounding")));

                // JSONObject opvragen van Status
                JSONObject status = parking.getJSONObject("parkingStatus");

                be.davygevaert.gentsefeesten.model.Status st = new be.davygevaert.gentsefeesten.model.Status();
                if (status.has("availableCapacity")) {      // last object in json-file has no availableCapacity !
                    st.setAvailableCapacity(Long.parseLong(status.getString("availableCapacity")));
                }
                st.setTotalCapacity(Long.parseLong(status.getString("totalCapacity")));
                st.setOpen(Boolean.valueOf(status.getString("open")));
                st.setSuggestedCapacity(status.getString("suggestedCapacity"));
                st.setActiveRoute(status.getString("activeRoute"));
                st.setLastModifiedDate(status.getString("lastModifiedDate"));
                p.setStatus(st);

                if (p.getCity().getName().equals("Gent")) {
                    arrayList.add(p);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
