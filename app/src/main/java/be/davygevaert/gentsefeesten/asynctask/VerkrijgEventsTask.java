package be.davygevaert.gentsefeesten.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import be.davygevaert.gentsefeesten.databank.EventDB;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.tools.JSON_Tools;

/**
 * Created by Davy on 14/06/2016.
 */
public class VerkrijgEventsTask extends AsyncTask<Object, Integer, EventDB> {

    private static String TAG = "VerkrijgEventsTask";

    private EventDB eventDB;
    private Context context;

    public VerkrijgEventsTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected EventDB doInBackground(Object... objects) {
        eventDB = new EventDB(context);

        try {

            // eerste object casten naar een String waarde
            String fileName = (String) objects[0];
            Log.i(TAG, "fileName inhoud : " + fileName);

            ObjectMapper mapper = new ObjectMapper();

            /*
            indien foutmelding op emulator voortvloeiend van JsonNode rootArray, zijnde :
            OutOfMemoryError: Failed to allocate a byte allocation with ... free bytes and 5MB until OOM

            naar avd manager gaan en de vm heap size van de emulator veranderen van 64mb naar 128mb
             */
            JsonNode rootArray = mapper.readTree(JSON_Tools.loadJSONFromAsset(fileName, context));

            for (JsonNode root : rootArray) {
                Event event = new Event();
                event.setId(root.path("id").asText());
                event.setActiviteitsId(root.path("activiteit_id").asText());
                event.setTitel(root.path("titel").asText());
                event.setModified(root.path("modified").asText());
                event.setOmschrijving(root.path("omschrijving").asText());
                event.setDatum(root.path("datum").asText());
                event.setPeriode(root.path("dag").asText());
                event.setStartuur(root.path("startuur").asText());
                event.setEinduur(root.path("einduur").asText());
                event.setTijdstip_sortering(root.path("tijdstip_sortering").asText());
                event.setUitmetvlieg(root.path("uitmetvlieg").asText());
                event.setInhetgents(root.path("inhetgents").asText());
                event.setAfbeelding(root.path("afbeelding").asText());
                event.setDoventolk(root.path("doventolk").asText());
                event.setOrganisatie(root.path("organisatie").asText());
                event.setOrganisator_id(root.path("organisator_id").asText());
                event.setGents_initiatief(root.path("gents_initiatief").asText());
                event.setVideos(root.path("videos").asText());
                event.setZoekwoorden(root.path("zoekwoorden").asText());
                event.setMeer_info(root.path("meer_info").asText());
                event.setFestival(root.path("festival").asText());
                event.setGhent_selection(root.path("ghent_selection").asText());
                event.setUrl(root.path("url").asText());
                event.setOrganisatie_website(root.path("organisatie_website").asText());
                event.setGratis(root.path("gratis").asText());
                event.setPrijs(root.path("prijs").asText());
                event.setPrijs_omschrijving(root.path("prijs_omschrijving").asText());
                event.setPrijs_vvk(root.path("prijs_vvk").asText());
                event.setPrijs_vvk_omschrijving(root.path("prijs_vvk_omschrijving").asText());
                event.setKorting(root.path("korting").asText());
                event.setCategorie_id(root.path("categorie_id").asText());
                event.setCategorie_naam(root.path("categorie_naam").asText());
                event.setToegankelijk_rolstoel(root.path("toegankelijk_rolstoel").asText());
                event.setOverkoepelende_titel(root.path("overkoepelende_titel").asText());
                event.setOverkoepelende_omschrijving(root.path("overkoepelende_omschrijving").asText());
                event.setLocatie_id(root.path("locatie_id").asText());
                event.setLocatie(root.path("locatie").asText());
                event.setSublocatie(root.path("sublocatie").asText());
                event.setStraat(root.path("straat").asText());
                event.setHuisnummer(root.path("huisnummer").asText());
                event.setBus(root.path("bus").asText());
                event.setPostcode(root.path("postcode").asText());
                event.setGemeente(root.path("gemeente").asText());
                event.setLatitude(root.path("latitude").asText());
                event.setLongitude(root.path("longitude").asText());
                event.setDagklapper(root.path("dagklapper").asText());

                eventDB.addEvent(event);
            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventDB;
    }

    @Override
    protected void onPostExecute(EventDB eventDB) {
        super.onPostExecute(eventDB);
    }
}

