package be.davygevaert.gentsefeesten.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.davygevaert.gentsefeesten.databank.EventDB;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.tools.JSON_Tools;

/**
 * Created by Davy on 14/06/2016.
 */
public class VerkrijgEventsTask extends AsyncTask<Object, Integer, EventDB> {

    private static String TAG = VerkrijgEventsTask.class.getSimpleName();


    private EventDB eventDB;
    private Context context;

    public VerkrijgEventsTask(Context ctx) {
        context = ctx;
    }

    /* sql query
    select *
    from tblEvent
    where _startuur is not null
    group by _startdatum_long
     */

    @Override
    protected EventDB doInBackground(Object... objects) {
        eventDB = new EventDB(context);

        try {

            // eerste object casten naar een String waarde
            String fileName = (String) objects[0];
            //Log.i(TAG, "fileName inhoud : " + fileName);

            ObjectMapper mapper = new ObjectMapper();

            /*
            indien foutmelding op emulator voortvloeiend van JsonNode rootArray, zijnde :
            OutOfMemoryError: Failed to allocate a byte allocation with ... free bytes and 5MB until OOM

            naar avd manager gaan en de vm heap size van de emulator veranderen van 64mb naar 128mb

            android:largeHeap="true" ook aanwezig in android manifest xml
             */

            JsonNode rootArray = mapper.readTree(JSON_Tools.loadJSONFromAsset(fileName, context));

            for (JsonNode root : rootArray) {
                Event event = new Event();
                event.setId(root.path("@id").asText());         // werkt
                event.setNaam(root.at("/name/nl").asText());  // werkt
                event.setType(root.path("@type").asText());     // werkt

                // werkt
                JsonNode contactPointNode = root.path("contactPoint");
                if (contactPointNode.isArray()){
                    for (final JsonNode objNode : contactPointNode) {
                        // Log.i(TAG, "objNode : " + objNode);
                        event.setContactPuntId(objNode.asText());
                    }
                }

                event.setBijdragerType(root.at("/contributor/@type").asText());         // werkt
                event.setBijdragerNaam(root.at("/contributor/name/nl").asText());     // werkt

                event.setBeschrijving(root.at("/description/nl").asText());     // werkt


                event.setAfbeeldingUrl(root.at("/image/url").asText());         // werkt
                event.setAfbeeldingThumbNail(root.at("/image/thumbnailUrl").asText());  // werkt
                event.setAfbeeldingTitel(root.at("/image/caption").asText());   // werkt

                // werkt
                JsonNode languageNode = root.path("inLanguage");
                if (languageNode.isArray()){
                    for (final JsonNode objNode : languageNode) {
                        // Log.i(TAG, "objNode : " + objNode);
                        event.setTaal(objNode.asText());
                    }
                }

                event.setIsGratisToegang(root.at("/isAccessibleForFree").asText());    // werkt

                // werkt
                JsonNode isPartOfNode = root.path("isPartOf");
                if (isPartOfNode.isArray()){
                    for (final JsonNode objNode : isPartOfNode) {
                        // Log.i(TAG, "objNode : " + objNode);
                        event.setMaaktDeelUitVan(objNode.asText());
                    }
                }

                event.setRolstoelToegankelijkheid(root.at("/isWheelchairUnfriendly").asText());    // werkt

                // werkt
                JsonNode keywordsNode = root.path("keywords");
                if (keywordsNode.isArray()){
                    for (final JsonNode objNode : keywordsNode) {
                        // Log.i(TAG, "objNode : " + objNode);
                        event.setKernwoorden(objNode.asText());
                    }
                }

                event.setLocatieId(root.at("/location").asText());  // werkt

                /* onderstaande om startdate en enddate zoals tijdstipsortering methode te laten verlopen uit 2016 */

                DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                String startDatum = root.at("/startDate").asText();
                // Log.i(TAG, "startDatum root " + startDatum);
                String startuur = root.at("/startDate").asText();
                String einduur = root.at("/endDate").asText();

                if (startDatum.contains("null")) {
                    // do nothing if null
                } else {
                    final ZonedDateTime parseStartDatum = ZonedDateTime.parse(startDatum, formatter);
                    if (parseStartDatum != null) {
                        // Log.i(TAG, "startdatum : " + parseStartDatum.getDayOfMonth() );
                        event.setStartDatumLong(startDatum);

                        if (parseStartDatum.getDayOfMonth() < 10) {
                            event.setStartDatumShort("0" + parseStartDatum.getDayOfMonth() + "/07");
                        } else {
                            event.setStartDatumShort("" + parseStartDatum.getDayOfMonth() + "/07");
                        }
                    }
                }

                if (startuur.contains("null")) {
                    // do nothing if null
                } else {
                    final ZonedDateTime parseStartUur = ZonedDateTime.parse(startuur, formatter);
                    if (parseStartUur != null) {
                        String uur = "";
                        if (parseStartUur.getHour() <= 9) {
                            uur = "0" + parseStartUur.getHour();
                        } else {
                            uur = "" + parseStartUur.getHour();
                        }

                        if (parseStartUur.getMinute() <= 9) {
                            uur += ":0" + parseStartUur.getMinute();
                        } else {
                            uur += ":" + parseStartUur.getMinute();
                        }
                        event.setStartUur(uur);
                    }
                }

                if (einduur.contains("null")) {
                    // do nothing if null
                } else {
                    final ZonedDateTime parseEindUur = ZonedDateTime.parse(einduur, formatter);
                    if (parseEindUur != null) {
                        String uur = "";
                        if (parseEindUur.getHour() <= 9) {
                            uur = "0" + parseEindUur.getHour();
                        } else {
                            uur = "" + parseEindUur.getHour();
                        }

                        if (parseEindUur.getMinute() <= 9) {
                            uur += ":0" + parseEindUur.getMinute();
                        } else {
                            uur += ":" + parseEindUur.getMinute();
                        }
                        event.setEindUur(uur);
                    }
                }

                event.setOrganisatorId(root.at("/organizer").asText()); // werkt


                // werkt
                JsonNode categorieNode = root.path("theme");
                if (categorieNode.isArray()){
                    for (final JsonNode objNode : categorieNode) {
                        // Log.i(TAG, "objNode : " + objNode);
                        event.setCategorieId(objNode.asText());
                    }
                }

                event.setWebsiteUrl(root.at("/url").asText());          // werkt

                // sharedpreferences firsttimer boolean uitgeschakeld voorlopig in Splashscreen

                // werkt niet ! array []
                JsonNode videoNode = root.path("video");
                if (videoNode.isArray()){
                    for (final JsonNode objNode : videoNode) {
                        // Log.i(TAG, "objNode : " + objNode);

                        // Log.i(TAG, "objNode video url: " + objNode.path("embedUrl"));
                        // Log.i(TAG, "objNode video thumbnail: " + objNode.path("thumbnail"));
                        // Log.i(TAG, "objNode video caption: " + objNode.path("caption"));

                        event.setVideoUrl(objNode.path("embedUrl").asText());
                        event.setVideoThumbnail(objNode.path("thumbnail").asText());
                        event.setVideoOnderschrift(objNode.path("caption").asText());

                        //event.setVideo(objNode.asText());
                    }
                }

                JsonNode offersNode = root.path("offers");
                if (offersNode.isArray()){
                    // Log.i(TAG, "offersNode is een array");
                    for (final JsonNode objNode : offersNode) {

                        /*
                        Log.i(TAG, "objNode price : " + objNode.path("price"));
                        Log.i(TAG, "objNode description: " + objNode.path("description"));
                        Log.i(TAG, "objNode currency: " + objNode.path("priceCurrency"));
                        Log.i(TAG, "objNode voorverkoopprijs: " + objNode.path("priceValidUntil"));
                        */
                        // arrays uitlezen gaat
                        // Log.i(TAG, "objNode korting: " + objNode.path("eligibleForDiscount"));
                        // Log.i(TAG, "objNode verkrijgbaarheid: " + objNode.path("availableAtOrFrom"));

                        event.setPrijs(objNode.path("price").asText());
                        event.setWisselkoers(objNode.path("priceCurrency").asText());
                        event.setPrijs_omschrijving(objNode.path("description").asText());
                        event.setVoorverkoopPrijs(objNode.path("priceValidUntil").asText());

                        /*
                        String korting = objNode.path("eligibleForDiscount").asText();
                        event.setVerkrijgbaarheid(objNode.path("eligibleForDiscount").asText());

                        String verkrijgbaarheid = objNode.path("availableAtOrFrom").asText();
                        event.setKorting(objNode.path("availableAtOrFrom").asText());
                        */

                        // arrays
                        JsonNode availableNode = objNode.path("availableAtOrFrom");
                        if (availableNode.isArray()) {
                            String availableData = "";

                            for (final JsonNode innerAvailableNode : availableNode) {
                                // Log.i(TAG, "innerAvailableNode : " + innerAvailableNode.asText());
                                availableData += innerAvailableNode.asText();
                                // elke availableData string met een komma en spatie erbij plaatsen
                                availableData += ", ";
                            }

                            if (availableData.isEmpty()) {
                                // do nothing
                            } else {
                                // de laatste 2 karakters (spatie en komma verwijderen)
                                availableData = availableData.substring(0, availableData.length() - 2);
                            }

                            event.setVerkrijgbaarheid(availableData);
                        }

                        JsonNode discountNode = objNode.path("eligibleForDiscount");
                        if (discountNode.isArray()) {
                            String discountData = "";
                            for (final JsonNode innerDiscountNode : discountNode) {
                                // Log.i(TAG, "innerDiscountNode : " + innerDiscountNode.asText());
                                discountData += innerDiscountNode.asText();
                                // elke discountData string met een komma en spatie erbij plaatsen
                                discountData += ", ";
                            }

                            if (discountData.isEmpty()) {
                                // do nothing
                            } else {
                                // de laatste 2 karakters (spatie en komma verwijderen)
                                discountData = discountData.substring(0, discountData.length() - 2);
                            }

                            event.setKorting(discountData);
                        }


                    }
                }

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
