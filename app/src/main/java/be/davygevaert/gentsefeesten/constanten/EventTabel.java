package be.davygevaert.gentsefeesten.constanten;

/**
 * Created by Davy on 16/06/2016.
 */
public interface EventTabel {
    // interface automatisch static en final

    String TABEL_NAAM = "tblEvent";
    String EVENT_ID = "_event_id";
    String EVENT_NAAM = "_naam";
    String EVENT_TYPE = "_event_type";
    String EVENT_CONTACTPOINT_ID = "_contactpoint_id";
    String EVENT_CONTRIBUTOR_TYPE = "_contributor_type";
    String EVENT_CONTRIBUTOR_NAME = "_contributor_name";
    String EVENT_BESCHRIJVING = "_beschrijving";
    String EVENT_AFBEELDING_URL = "_afbeelding_url";
    String EVENT_AFBEELDING_THUMBNAIL = "_afbeelding_thumbnail";
    String EVENT_AFBEELDING_TITEL = "_afbeelding_titel";
    String EVENT_TAAL = "_taal";
    String EVENT_IS_ACCESSIBLE_FOR_FREE = "_is_accessible_for_free";
    String EVENT_IS_PART_OF = "_is_part_of";
    String EVENT_ROLSTOELTOEGANKELIJKHEID = "_rolstoeltoegankelijkheid";
    String EVENT_KERNWOORDEN = "_kernwoorden";
    String EVENT_LOCATIE_ID = "_locatie_Id";
    String EVENT_STARTDATUM_LONG = "_startdatum_long";
    String EVENT_STARTDATUM_SHORT = "_startdatum_short";
    String EVENT_STARTUUR = "_startuur";
    String EVENT_EINDUUR = "_einduur";
    String EVENT_ORGANISATOR_ID = "_organisator_id";
    String EVENT_CATEGORIE_ID = "_categorie_id";      // json categorie => 'theme'
    String EVENT_WEBSITE_URL = "_website_url";
    String EVENT_VIDEO_URL = "_video_url";
    String EVENT_VIDEO_THUMBNAIL = "_video_thumbnail";
    String EVENT_VIDEO_ONDERSCHRIFT = "_video_onderschrift";
    String EVENT_PRIJS = "_prijs";
    String EVENT_WISSELKOERS = "_wisselkoers";
    String EVENT_PRIJS_OMSCHRIJVING = "_prijs_omschrijving";
    String EVENT_VOORVERKOOPPRIJS = "_voorverkoopprijs";        // json => priceValidUntil, wordt enkel gebruikt indien dit niet NULL is
    String EVENT_VERKRIJGBAARHEID = "_verkrijgbaarheid";        // json => availableAtOrFrom
    String EVENT_KORTING = "_korting";                          // json => eligibleForDiscount



}