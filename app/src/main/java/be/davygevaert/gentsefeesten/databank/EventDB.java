package be.davygevaert.gentsefeesten.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.constanten.EventTabel;
import be.davygevaert.gentsefeesten.model.Event;

/**
 * Created by Davy on 17/06/2016.
 */
public class EventDB extends SchemaHelper {

    private SQLiteDatabase sd;
    private ArrayList<Event> eventsArrayLijst;

    public EventDB(Context context) {
        super(context);
    }

    public long addEvent(Event event) {
        // AANMAAK VAN EEN CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();

        cv.put(EventTabel.ID, event.getId());
        cv.put(EventTabel.ACTIVITEIT_ID, event.getActiviteitsId());
        cv.put(EventTabel.TITEL, event.getTitel());
        cv.put(EventTabel.MODIFIED, event.getModified());
        cv.put(EventTabel.OMSCHRIJVING, event.getOmschrijving());
        cv.put(EventTabel.DATUM, event.getDatum());
        cv.put(EventTabel.PERIODE, event.getPeriode());
        cv.put(EventTabel.STARTUUR, event.getStartuur());
        cv.put(EventTabel.EINDUUR, event.getEinduur());
        cv.put(EventTabel.TIJDSTIP_SORTERING, event.getTijdstip_sortering());

        cv.put(EventTabel.UIT_MET_VLIEG, event.getUitmetvlieg());
        cv.put(EventTabel.IN_HET_GENTS, event.getInhetgents());
        cv.put(EventTabel.AFBEELDING, event.getAfbeelding());
        cv.put(EventTabel.DOVENTOLK, event.getDoventolk());
        cv.put(EventTabel.ORGANISATIE, event.getOrganisatie());
        cv.put(EventTabel.ORGANISATOR_ID, event.getOrganisator_id());
        cv.put(EventTabel.GENTS_INITIATIEF, event.getGents_initiatief());
        cv.put(EventTabel.VIDEOS, event.getVideos());
        cv.put(EventTabel.ZOEKWOORDEN, event.getZoekwoorden());
        cv.put(EventTabel.MEER_INFO, event.getMeer_info());

        cv.put(EventTabel.FESTIVAL, event.getFestival());
        cv.put(EventTabel.GHENT_SELECTION, event.getGhent_selection());
        cv.put(EventTabel.URL, event.getUrl());
        cv.put(EventTabel.ORGANISATIE_WEBSITE, event.getOrganisatie_website());
        cv.put(EventTabel.GRATIS, event.getGratis());
        cv.put(EventTabel.APARTE_PRIJS_VVK, event.getAparte_prijs_vvk());
        cv.put(EventTabel.PRIJS, event.getPrijs());
        cv.put(EventTabel.PRIJS_OMSCHRIJVING, event.getPrijs_omschrijving());
        cv.put(EventTabel.PRIJS_VVK, event.getPrijs_vvk());
        cv.put(EventTabel.PRIJS_VVK_OMSCHRIJVING, event.getPrijs_vvk_omschrijving());

        cv.put(EventTabel.KORTING, event.getKorting());
        cv.put(EventTabel.CATEGORIE_ID, event.getCategorie_id());
        cv.put(EventTabel.CATEGORIE_TITEL, event.getCategorie_naam());
        cv.put(EventTabel.TOEGANKELIJK_ROLSTOEL, event.getToegankelijk_rolstoel());
        cv.put(EventTabel.OVERKOEPELENDE_TITEL, event.getModified());
        cv.put(EventTabel.OVERKOEPELENDE_OMSCHRIJVING, event.getOverkoepelende_omschrijving());
        cv.put(EventTabel.LOCATIE_ID, event.getLocatie_id());
        cv.put(EventTabel.LOCATIE_NAAM, event.getLocatie());
        cv.put(EventTabel.SUBLOCATIE, event.getSublocatie());
        cv.put(EventTabel.STRAAT, event.getStraat());

        cv.put(EventTabel.HUISNUMMER, event.getHuisnummer());
        cv.put(EventTabel.BUS, event.getBus());
        cv.put(EventTabel.POSTCODE, event.getPostcode());
        cv.put(EventTabel.GEMEENTE, event.getGemeente());
        cv.put(EventTabel.LATITUDE, event.getLatitude());
        cv.put(EventTabel.LONGITUDE, event.getLongitude());
        cv.put(EventTabel.DAGKLAPPER, event.getDagklapper());

        // BEKOMEN VAN EEN SCHRIJFBARE DATABANK EN VERVOLGENS DE RECORD TOEVOEGEN
        sd = this.getWritableDatabase();

        long result = sd.insert(EventTabel.TABEL_NAAM, EventTabel.TITEL, cv);
        return result;
    }

    public ArrayList<Event> getEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();
            String QUERY = "SELECT * FROM "+ EventTabel.TABEL_NAAM + " " +
                            "ORDER BY tblEvent._tijdstip_sortering";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setActiviteitsId(cursor.getString(1));
                    event.setTitel(cursor.getString(2));
                    event.setModified(cursor.getString(3));
                    event.setOmschrijving(cursor.getString(4));
                    event.setDatum(cursor.getString(5));
                    event.setPeriode(cursor.getString(6));
                    event.setStartuur(cursor.getString(7));
                    event.setEinduur(cursor.getString(8));
                    event.setTijdstip_sortering(cursor.getString(9));

                    event.setUitmetvlieg(cursor.getString(10));
                    event.setInhetgents(cursor.getString(11));
                    event.setAfbeelding(cursor.getString(12));
                    event.setDoventolk(cursor.getString(13));
                    event.setOrganisatie(cursor.getString(14));
                    event.setOrganisator_id(cursor.getString(15));
                    event.setGents_initiatief(cursor.getString(16));
                    event.setVideos(cursor.getString(17));
                    event.setZoekwoorden(cursor.getString(18));
                    event.setMeer_info(cursor.getString(19));

                    event.setFestival(cursor.getString(20));
                    event.setGhent_selection(cursor.getString(21));
                    event.setUrl(cursor.getString(22));
                    event.setOrganisatie_website(cursor.getString(23));
                    event.setGratis(cursor.getString(24));
                    event.setAparte_prijs_vvk(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setPrijs_omschrijving(cursor.getString(27));
                    event.setPrijs_vvk(cursor.getString(28));
                    event.setPrijs_vvk_omschrijving(cursor.getString(29));

                    event.setKorting(cursor.getString(30));
                    event.setCategorie_id(cursor.getString(31));
                    event.setCategorie_naam(cursor.getString(32));
                    event.setToegankelijk_rolstoel(cursor.getString(33));
                    event.setOverkoepelende_titel(cursor.getString(34));
                    event.setOverkoepelende_omschrijving(cursor.getString(35));
                    event.setLocatie_id(cursor.getString(36));
                    event.setLocatie(cursor.getString(37));
                    event.setSublocatie(cursor.getString(38));
                    event.setStraat(cursor.getString(39));

                    event.setHuisnummer(cursor.getString(40));
                    event.setBus(cursor.getString(41));
                    event.setPostcode(cursor.getString(42));
                    event.setGemeente(cursor.getString(43));
                    event.setLatitude(cursor.getString(44));
                    event.setLongitude(cursor.getString(45));
                    event.setDagklapper(cursor.getString(46));

                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public ArrayList<Event> getEventsByDate(int timestamp) {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();
            String QUERY = "SELECT * FROM "+ EventTabel.TABEL_NAAM + " " +
                    "WHERE tblEvent._datum = " + timestamp + " " +
                    "ORDER BY tblEvent._tijdstip_sortering";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setActiviteitsId(cursor.getString(1));
                    event.setTitel(cursor.getString(2));
                    event.setModified(cursor.getString(3));
                    event.setOmschrijving(cursor.getString(4));
                    event.setDatum(cursor.getString(5));
                    event.setPeriode(cursor.getString(6));
                    event.setStartuur(cursor.getString(7));
                    event.setEinduur(cursor.getString(8));
                    event.setTijdstip_sortering(cursor.getString(9));

                    event.setUitmetvlieg(cursor.getString(10));
                    event.setInhetgents(cursor.getString(11));
                    event.setAfbeelding(cursor.getString(12));
                    event.setDoventolk(cursor.getString(13));
                    event.setOrganisatie(cursor.getString(14));
                    event.setOrganisator_id(cursor.getString(15));
                    event.setGents_initiatief(cursor.getString(16));
                    event.setVideos(cursor.getString(17));
                    event.setZoekwoorden(cursor.getString(18));
                    event.setMeer_info(cursor.getString(19));

                    event.setFestival(cursor.getString(20));
                    event.setGhent_selection(cursor.getString(21));
                    event.setUrl(cursor.getString(22));
                    event.setOrganisatie_website(cursor.getString(23));
                    event.setGratis(cursor.getString(24));
                    event.setAparte_prijs_vvk(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setPrijs_omschrijving(cursor.getString(27));
                    event.setPrijs_vvk(cursor.getString(28));
                    event.setPrijs_vvk_omschrijving(cursor.getString(29));

                    event.setKorting(cursor.getString(30));
                    event.setCategorie_id(cursor.getString(31));
                    event.setCategorie_naam(cursor.getString(32));
                    event.setToegankelijk_rolstoel(cursor.getString(33));
                    event.setOverkoepelende_titel(cursor.getString(34));
                    event.setOverkoepelende_omschrijving(cursor.getString(35));
                    event.setLocatie_id(cursor.getString(36));
                    event.setLocatie(cursor.getString(37));
                    event.setSublocatie(cursor.getString(38));
                    event.setStraat(cursor.getString(39));

                    event.setHuisnummer(cursor.getString(40));
                    event.setBus(cursor.getString(41));
                    event.setPostcode(cursor.getString(42));
                    event.setGemeente(cursor.getString(43));
                    event.setLatitude(cursor.getString(44));
                    event.setLongitude(cursor.getString(45));
                    event.setDagklapper(cursor.getString(46));

                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public ArrayList<Event> getEventsByCategoryAndDate(int cat_id, int date) {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();
            String QUERY = "SELECT * FROM " + EventTabel.TABEL_NAAM + " " +
                            "WHERE tblEvent._categorie_id = " + cat_id + " " +
                            "AND " + "tblEvent._datum = " + date + " " +
                            "ORDER BY tblEvent._tijdstip_sortering";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setActiviteitsId(cursor.getString(1));
                    event.setTitel(cursor.getString(2));
                    event.setModified(cursor.getString(3));
                    event.setOmschrijving(cursor.getString(4));
                    event.setDatum(cursor.getString(5));
                    event.setPeriode(cursor.getString(6));
                    event.setStartuur(cursor.getString(7));
                    event.setEinduur(cursor.getString(8));
                    event.setTijdstip_sortering(cursor.getString(9));

                    event.setUitmetvlieg(cursor.getString(10));
                    event.setInhetgents(cursor.getString(11));
                    event.setAfbeelding(cursor.getString(12));
                    event.setDoventolk(cursor.getString(13));
                    event.setOrganisatie(cursor.getString(14));
                    event.setOrganisator_id(cursor.getString(15));
                    event.setGents_initiatief(cursor.getString(16));
                    event.setVideos(cursor.getString(17));
                    event.setZoekwoorden(cursor.getString(18));
                    event.setMeer_info(cursor.getString(19));

                    event.setFestival(cursor.getString(20));
                    event.setGhent_selection(cursor.getString(21));
                    event.setUrl(cursor.getString(22));
                    event.setOrganisatie_website(cursor.getString(23));
                    event.setGratis(cursor.getString(24));
                    event.setAparte_prijs_vvk(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setPrijs_omschrijving(cursor.getString(27));
                    event.setPrijs_vvk(cursor.getString(28));
                    event.setPrijs_vvk_omschrijving(cursor.getString(29));

                    event.setKorting(cursor.getString(30));
                    event.setCategorie_id(cursor.getString(31));
                    event.setCategorie_naam(cursor.getString(32));
                    event.setToegankelijk_rolstoel(cursor.getString(33));
                    event.setOverkoepelende_titel(cursor.getString(34));
                    event.setOverkoepelende_omschrijving(cursor.getString(35));
                    event.setLocatie_id(cursor.getString(36));
                    event.setLocatie(cursor.getString(37));
                    event.setSublocatie(cursor.getString(38));
                    event.setStraat(cursor.getString(39));

                    event.setHuisnummer(cursor.getString(40));
                    event.setBus(cursor.getString(41));
                    event.setPostcode(cursor.getString(42));
                    event.setGemeente(cursor.getString(43));
                    event.setLatitude(cursor.getString(44));
                    event.setLongitude(cursor.getString(45));
                    event.setDagklapper(cursor.getString(46));

                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public ArrayList<Event> getEventsByLocationAndDate(int locatie_id, int date) {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();
            String QUERY = "SELECT * FROM " + EventTabel.TABEL_NAAM + " " +
                    "WHERE tblEvent._locatie_id = " + locatie_id + " " +
                    "AND " + "tblEvent._datum = " + date + " " +
                    "ORDER BY tblEvent._tijdstip_sortering";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();
                    event.setId(cursor.getString(0));
                    event.setActiviteitsId(cursor.getString(1));
                    event.setTitel(cursor.getString(2));
                    event.setModified(cursor.getString(3));
                    event.setOmschrijving(cursor.getString(4));
                    event.setDatum(cursor.getString(5));
                    event.setPeriode(cursor.getString(6));
                    event.setStartuur(cursor.getString(7));
                    event.setEinduur(cursor.getString(8));
                    event.setTijdstip_sortering(cursor.getString(9));
                    event.setUitmetvlieg(cursor.getString(10));
                    event.setInhetgents(cursor.getString(11));
                    event.setAfbeelding(cursor.getString(12));
                    event.setDoventolk(cursor.getString(13));
                    event.setOrganisatie(cursor.getString(14));
                    event.setOrganisator_id(cursor.getString(15));
                    event.setGents_initiatief(cursor.getString(16));
                    event.setVideos(cursor.getString(17));
                    event.setZoekwoorden(cursor.getString(18));
                    event.setMeer_info(cursor.getString(19));
                    event.setFestival(cursor.getString(20));
                    event.setGhent_selection(cursor.getString(21));
                    event.setUrl(cursor.getString(22));
                    event.setOrganisatie_website(cursor.getString(23));
                    event.setGratis(cursor.getString(24));
                    event.setAparte_prijs_vvk(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setPrijs_omschrijving(cursor.getString(27));
                    event.setPrijs_vvk(cursor.getString(28));
                    event.setPrijs_vvk_omschrijving(cursor.getString(29));
                    event.setKorting(cursor.getString(30));
                    event.setCategorie_id(cursor.getString(31));
                    event.setCategorie_naam(cursor.getString(32));
                    event.setToegankelijk_rolstoel(cursor.getString(33));
                    event.setOverkoepelende_titel(cursor.getString(34));
                    event.setOverkoepelende_omschrijving(cursor.getString(35));
                    event.setLocatie_id(cursor.getString(36));
                    event.setLocatie(cursor.getString(37));
                    event.setSublocatie(cursor.getString(38));
                    event.setStraat(cursor.getString(39));
                    event.setHuisnummer(cursor.getString(40));
                    event.setBus(cursor.getString(41));
                    event.setPostcode(cursor.getString(42));
                    event.setGemeente(cursor.getString(43));
                    event.setLatitude(cursor.getString(44));
                    event.setLongitude(cursor.getString(45));
                    event.setDagklapper(cursor.getString(46));
                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public int getEventCount() {
        return eventsArrayLijst.size();
    }
}

