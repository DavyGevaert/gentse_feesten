package be.davygevaert.gentsefeesten.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.constanten.EventTabel;
import be.davygevaert.gentsefeesten.model.Categorie;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.model.Locatie;
import be.davygevaert.gentsefeesten.model.Organisator;

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

        cv.put(EventTabel.EVENT_ID, event.getId());
        cv.put(EventTabel.EVENT_NAAM, event.getNaam());
        cv.put(EventTabel.EVENT_TYPE, event.getType());
        cv.put(EventTabel.EVENT_CONTACTPOINT_ID, event.getContactPuntId());
        cv.put(EventTabel.EVENT_CONTRIBUTOR_TYPE, event.getBijdragerType());
        cv.put(EventTabel.EVENT_CONTRIBUTOR_NAME, event.getBijdragerNaam());
        cv.put(EventTabel.EVENT_BESCHRIJVING, event.getBeschrijving());
        cv.put(EventTabel.EVENT_AFBEELDING_URL, event.getAfbeeldingUrl());
        cv.put(EventTabel.EVENT_AFBEELDING_TITEL, event.getAfbeeldingTitel());
        cv.put(EventTabel.EVENT_AFBEELDING_THUMBNAIL, event.getAfbeeldingThumbNail());

        cv.put(EventTabel.EVENT_TAAL, event.getTaal());
        cv.put(EventTabel.EVENT_IS_ACCESSIBLE_FOR_FREE, event.getIsGratisToegang());
        cv.put(EventTabel.EVENT_IS_PART_OF, event.getMaaktDeelUitVan());
        cv.put(EventTabel.EVENT_ROLSTOELTOEGANKELIJKHEID, event.getRolstoelToegankelijkheid());
        cv.put(EventTabel.EVENT_KERNWOORDEN, event.getKernwoorden());
        cv.put(EventTabel.EVENT_LOCATIE_ID, event.getLocatieId());
        cv.put(EventTabel.EVENT_STARTDATUM_LONG, event.getStartDatumLong());
        cv.put(EventTabel.EVENT_STARTDATUM_SHORT, event.getStartDatumShort());
        cv.put(EventTabel.EVENT_STARTUUR, event.getStartUur());
        cv.put(EventTabel.EVENT_EINDUUR, event.getEindUur());
        cv.put(EventTabel.EVENT_ORGANISATOR_ID, event.getOrganisatorId());

        cv.put(EventTabel.EVENT_CATEGORIE_ID, event.getCategorieId());
        cv.put(EventTabel.EVENT_WEBSITE_URL, event.getWebsiteUrl());
        cv.put(EventTabel.EVENT_VIDEO_URL, event.getVideoUrl());
        cv.put(EventTabel.EVENT_VIDEO_THUMBNAIL, event.getVideoThumbnail());
        cv.put(EventTabel.EVENT_VIDEO_ONDERSCHRIFT, event.getVideoOnderschrift());
        cv.put(EventTabel.EVENT_PRIJS, event.getPrijs());
        cv.put(EventTabel.EVENT_WISSELKOERS, event.getWisselkoers());
        cv.put(EventTabel.EVENT_PRIJS_OMSCHRIJVING, event.getPrijs_omschrijving());
        cv.put(EventTabel.EVENT_VOORVERKOOPPRIJS, event.getVoorverkoopPrijs());
        cv.put(EventTabel.EVENT_VERKRIJGBAARHEID, event.getVerkrijgbaarheid());
        cv.put(EventTabel.EVENT_KORTING, event.getKorting());

        // BEKOMEN VAN EEN SCHRIJFBARE DATABANK EN VERVOLGENS DE RECORD TOEVOEGEN
        sd = this.getWritableDatabase();

        long result = sd.insert(EventTabel.TABEL_NAAM, EventTabel.EVENT_NAAM, cv);
        return result;
    }

    public ArrayList<Event> getAllDataShortNotation() {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();

            String QUERY = "select * " +
                            "from " + EventTabel.TABEL_NAAM + " " +
                            "where _startdatum_short is not null " +
                            "group by _startdatum_short " +
                            "order by _startdatum_long ASC ";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setNaam(cursor.getString(1));
                    event.setType(cursor.getString(2));
                    event.setContactPuntId(cursor.getString(3));
                    event.setBijdragerType(cursor.getString(4));
                    event.setBijdragerNaam(cursor.getString(5));
                    event.setBeschrijving(cursor.getString(6));
                    event.setAfbeeldingUrl(cursor.getString(7));
                    event.setAfbeeldingThumbNail(cursor.getString(8));
                    event.setAfbeeldingTitel(cursor.getString(9));
                    event.setTaal(cursor.getString(10));
                    event.setIsGratisToegang(cursor.getString(11));
                    event.setMaaktDeelUitVan(cursor.getString(12));
                    event.setRolstoelToegankelijkheid(cursor.getString(13));
                    event.setKernwoorden(cursor.getString(14));
                    event.setLocatieId(cursor.getString(15));
                    event.setStartDatumLong(cursor.getString(16));
                    event.setStartDatumShort(cursor.getString(17));
                    event.setStartUur(cursor.getString(18));
                    event.setEindUur(cursor.getString(19));
                    event.setOrganisatorId(cursor.getString(20));
                    event.setCategorieId(cursor.getString(21));
                    event.setWebsiteUrl(cursor.getString(22));
                    event.setVideoUrl(cursor.getString(23));
                    event.setVideoThumbnail(cursor.getString(24));
                    event.setVideoOnderschrift(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setWisselkoers(cursor.getString(27));
                    event.setPrijs_omschrijving(cursor.getString(28));
                    event.setVoorverkoopPrijs(cursor.getString(29));
                    event.setVerkrijgbaarheid(cursor.getString(30));
                    event.setKorting(cursor.getString(31));

                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public ArrayList<Event> getEventsByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();

            String QUERY = "SELECT DISTINCT e._event_id, e._naam, e._event_type, e._contactpoint_id, e._contributor_type, e._contributor_name, e._beschrijving,\n" +
                    "       e._afbeelding_url, e._afbeelding_thumbnail, e._afbeelding_titel, e._taal, e._is_accessible_for_free, e._is_part_of,\n" +
                    "       e._rolstoeltoegankelijkheid, e._kernwoorden, e._locatie_Id, e._startdatum_long, e._startdatum_short,\n" +
                    "       e._startuur, e._einduur, e._organisator_id, e._categorie_id, e._website_url," +
                    "       e._video_url, e._video_thumbnail, e._video_onderschrift, e._prijs, e._wisselkoers,\n" +
                    "       e._prijs_omschrijving, e._voorverkoopprijs, e._verkrijgbaarheid, e._korting,\n" +
                    "       c._categorie_titel,\n" +
                    "       l._locatie_naam, l._locatie_straat, l._locatie_postcode, l._locatie_gemeente, l._rolstoel_toegankelijkheid,\n" +
                    "       o._organisatie_naam, o._organisatie_straat, o._organisatie_postcode, o._organisatie_gemeente\n" +
                    "FROM tblEvent e\n" +
                    "INNER JOIN tblCategorie c ON e._categorie_id = c._categorie_id\n" +
                    "INNER JOIN tblLocatie l on e._locatie_Id = l._locatie_id\n" +
                    "INNER JOIN tblOrganisatie o on e._organisator_id = o._organisatie_id\n" +
                    "WHERE e._startdatum_short = '" + date + "'\n" +
                    "ORDER BY e._startdatum_long ASC";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setNaam(cursor.getString(1));
                    event.setType(cursor.getString(2));
                    event.setContactPuntId(cursor.getString(3));
                    event.setBijdragerType(cursor.getString(4));
                    event.setBijdragerNaam(cursor.getString(5));
                    event.setBeschrijving(cursor.getString(6));
                    event.setAfbeeldingUrl(cursor.getString(7));
                    event.setAfbeeldingThumbNail(cursor.getString(8));
                    event.setAfbeeldingTitel(cursor.getString(9));
                    event.setTaal(cursor.getString(10));
                    event.setIsGratisToegang(cursor.getString(11));
                    event.setMaaktDeelUitVan(cursor.getString(12));
                    event.setRolstoelToegankelijkheid(cursor.getString(13));
                    event.setKernwoorden(cursor.getString(14));
                    event.setLocatieId(cursor.getString(15));
                    event.setStartDatumLong(cursor.getString(16));
                    event.setStartDatumShort(cursor.getString(17));
                    event.setStartUur(cursor.getString(18));
                    event.setEindUur(cursor.getString(19));
                    event.setOrganisatorId(cursor.getString(20));
                    event.setCategorieId(cursor.getString(21));
                    event.setWebsiteUrl(cursor.getString(22));
                    event.setVideoUrl(cursor.getString(23));
                    event.setVideoThumbnail(cursor.getString(24));
                    event.setVideoOnderschrift(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setWisselkoers(cursor.getString(27));
                    event.setPrijs_omschrijving(cursor.getString(28));
                    event.setVoorverkoopPrijs(cursor.getString(29));
                    event.setVerkrijgbaarheid(cursor.getString(30));
                    event.setKorting(cursor.getString(31));

                    Categorie categorie = new Categorie();
                    categorie.setTitel(cursor.getString(32));
                    event.setCategorie(categorie);

                    Locatie locatie = new Locatie();
                    locatie.setNaam(cursor.getString(33));
                    locatie.setStraat(cursor.getString(34));
                    locatie.setPostcode(cursor.getString(35));
                    locatie.setGemeente(cursor.getString(36));
                    locatie.setRolstoel_toegankelijkheid(cursor.getString(37));
                    event.setLocatie(locatie);

                    Organisator organisator = new Organisator();
                    organisator.setOrganisatieNaam(cursor.getString(38));
                    organisator.setOrganisatieStraat(cursor.getString(39));
                    organisator.setOrganisatiePostcode(cursor.getString(40));
                    organisator.setOrganisatieGemeente(cursor.getString(41));
                    event.setOrganisator(organisator);

                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public ArrayList<Event> getEventsByCategoryAndDate(String cat_id, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();
            String QUERY = "SELECT DISTINCT e._event_id, e._naam, e._event_type, e._contactpoint_id, e._contributor_type, e._contributor_name, e._beschrijving,\n" +
                    "e._afbeelding_url, e._afbeelding_thumbnail, e._afbeelding_titel, e._taal, e._is_accessible_for_free, e._is_part_of,\n" +
                    "e._rolstoeltoegankelijkheid, e._kernwoorden, e._locatie_Id, e._startdatum_long, e._startdatum_short,\n" +
                    "e._startuur, e._einduur, e._organisator_id, e._categorie_id, e._website_url," +
                    "e._video_url, e._video_thumbnail, e._video_onderschrift, e._prijs, e._wisselkoers,\n" +
                    "e._prijs_omschrijving, e._voorverkoopprijs, e._verkrijgbaarheid, e._korting,\n" +
                    "c._categorie_titel,\n" +
                    "l._locatie_naam, l._locatie_straat, l._locatie_postcode, l._locatie_gemeente, l._rolstoel_toegankelijkheid,\n" +
                    "o._organisatie_naam, o._organisatie_straat, o._organisatie_postcode, o._organisatie_gemeente\n" +
                    "FROM tblEvent e\n" +
                    "INNER JOIN tblCategorie c ON e._categorie_id = c._categorie_id\n" +
                    "INNER JOIN tblLocatie l on e._locatie_Id = l._locatie_id\n" +
                    "INNER JOIN tblOrganisatie o on e._organisator_id = o._organisatie_id\n" +
                    "WHERE e._categorie_id = '" + cat_id +  "'" +
                    "AND e._startdatum_short = '" + date + "'" +
                    "ORDER BY e._startdatum_long ASC";

            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setNaam(cursor.getString(1));
                    event.setType(cursor.getString(2));
                    event.setContactPuntId(cursor.getString(3));
                    event.setBijdragerType(cursor.getString(4));
                    event.setBijdragerNaam(cursor.getString(5));
                    event.setBeschrijving(cursor.getString(6));
                    event.setAfbeeldingUrl(cursor.getString(7));
                    event.setAfbeeldingThumbNail(cursor.getString(8));
                    event.setAfbeeldingTitel(cursor.getString(9));
                    event.setTaal(cursor.getString(10));
                    event.setIsGratisToegang(cursor.getString(11));
                    event.setMaaktDeelUitVan(cursor.getString(12));
                    event.setRolstoelToegankelijkheid(cursor.getString(13));
                    event.setKernwoorden(cursor.getString(14));
                    event.setLocatieId(cursor.getString(15));
                    event.setStartDatumLong(cursor.getString(16));
                    event.setStartDatumShort(cursor.getString(17));
                    event.setStartUur(cursor.getString(18));
                    event.setEindUur(cursor.getString(19));
                    event.setOrganisatorId(cursor.getString(20));
                    event.setCategorieId(cursor.getString(21));
                    event.setWebsiteUrl(cursor.getString(22));
                    event.setVideoUrl(cursor.getString(23));
                    event.setVideoThumbnail(cursor.getString(24));
                    event.setVideoOnderschrift(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setWisselkoers(cursor.getString(27));
                    event.setPrijs_omschrijving(cursor.getString(28));
                    event.setVoorverkoopPrijs(cursor.getString(29));
                    event.setVerkrijgbaarheid(cursor.getString(30));
                    event.setKorting(cursor.getString(31));

                    Categorie categorie = new Categorie();
                    categorie.setTitel(cursor.getString(32));
                    event.setCategorie(categorie);

                    Locatie locatie = new Locatie();
                    locatie.setNaam(cursor.getString(33));
                    locatie.setStraat(cursor.getString(34));
                    locatie.setPostcode(cursor.getString(35));
                    locatie.setGemeente(cursor.getString(36));
                    locatie.setRolstoel_toegankelijkheid(cursor.getString(37));
                    event.setLocatie(locatie);

                    Organisator organisator = new Organisator();
                    organisator.setOrganisatieNaam(cursor.getString(38));
                    organisator.setOrganisatieStraat(cursor.getString(39));
                    organisator.setOrganisatiePostcode(cursor.getString(40));
                    organisator.setOrganisatieGemeente(cursor.getString(41));
                    event.setOrganisator(organisator);

                    eventsArrayLijst.add(event);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return eventsArrayLijst;
    }

    public ArrayList<Event> getEventsByLocationAndDate(String locatie_id, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        eventsArrayLijst = null;
        try{
            eventsArrayLijst = new ArrayList<Event>();

            String QUERY = "SELECT DISTINCT e._event_id, e._naam, e._event_type, e._contactpoint_id, e._contributor_type, e._contributor_name, e._beschrijving,\n" +
                    "e._afbeelding_url, e._afbeelding_thumbnail, e._afbeelding_titel, e._taal, e._is_accessible_for_free, e._is_part_of,\n" +
                    "e._rolstoeltoegankelijkheid, e._kernwoorden, e._locatie_Id, e._startdatum_long, e._startdatum_short,\n" +
                    "e._startuur, e._einduur, e._organisator_id, e._categorie_id, e._website_url," +
                    "e._video_url, e._video_thumbnail, e._video_onderschrift, e._prijs, e._wisselkoers,\n" +
                    "e._prijs_omschrijving, e._voorverkoopprijs, e._verkrijgbaarheid, e._korting,\n" +
                    "c._categorie_titel,\n" +
                    "l._locatie_naam, l._locatie_straat, l._locatie_postcode, l._locatie_gemeente, l._rolstoel_toegankelijkheid,\n" +
                    "o._organisatie_naam, o._organisatie_straat, o._organisatie_postcode, o._organisatie_gemeente\n" +
                    "FROM tblEvent e\n" +
                    "INNER JOIN tblCategorie c ON e._categorie_id = c._categorie_id\n" +
                    "INNER JOIN tblLocatie l on e._locatie_Id = l._locatie_id\n" +
                    "INNER JOIN tblOrganisatie o on e._organisator_id = o._organisatie_id\n" +
                    "WHERE e._locatie_id = '" + locatie_id + "' " +
                    "AND e._startdatum_short = '" + date + "' " +
                    "ORDER BY e._startdatum_long ASC";

            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Event event = new Event();

                    event.setId(cursor.getString(0));
                    event.setNaam(cursor.getString(1));
                    event.setType(cursor.getString(2));
                    event.setContactPuntId(cursor.getString(3));
                    event.setBijdragerType(cursor.getString(4));
                    event.setBijdragerNaam(cursor.getString(5));
                    event.setBeschrijving(cursor.getString(6));
                    event.setAfbeeldingUrl(cursor.getString(7));
                    event.setAfbeeldingThumbNail(cursor.getString(8));
                    event.setAfbeeldingTitel(cursor.getString(9));
                    event.setTaal(cursor.getString(10));
                    event.setIsGratisToegang(cursor.getString(11));
                    event.setMaaktDeelUitVan(cursor.getString(12));
                    event.setRolstoelToegankelijkheid(cursor.getString(13));
                    event.setKernwoorden(cursor.getString(14));
                    event.setLocatieId(cursor.getString(15));
                    event.setStartDatumLong(cursor.getString(16));
                    event.setStartDatumShort(cursor.getString(17));
                    event.setStartUur(cursor.getString(18));
                    event.setEindUur(cursor.getString(19));
                    event.setOrganisatorId(cursor.getString(20));
                    event.setCategorieId(cursor.getString(21));
                    event.setWebsiteUrl(cursor.getString(22));
                    event.setVideoUrl(cursor.getString(23));
                    event.setVideoThumbnail(cursor.getString(24));
                    event.setVideoOnderschrift(cursor.getString(25));
                    event.setPrijs(cursor.getString(26));
                    event.setWisselkoers(cursor.getString(27));
                    event.setPrijs_omschrijving(cursor.getString(28));
                    event.setVoorverkoopPrijs(cursor.getString(29));
                    event.setVerkrijgbaarheid(cursor.getString(30));
                    event.setKorting(cursor.getString(31));

                    Categorie categorie = new Categorie();
                    categorie.setTitel(cursor.getString(32));
                    event.setCategorie(categorie);

                    Locatie locatie = new Locatie();
                    locatie.setNaam(cursor.getString(33));
                    locatie.setStraat(cursor.getString(34));
                    locatie.setPostcode(cursor.getString(35));
                    locatie.setGemeente(cursor.getString(36));
                    locatie.setRolstoel_toegankelijkheid(cursor.getString(37));
                    event.setLocatie(locatie);

                    Organisator organisator = new Organisator();
                    organisator.setOrganisatieNaam(cursor.getString(38));
                    organisator.setOrganisatieStraat(cursor.getString(39));
                    organisator.setOrganisatiePostcode(cursor.getString(40));
                    organisator.setOrganisatieGemeente(cursor.getString(41));
                    event.setOrganisator(organisator);

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
