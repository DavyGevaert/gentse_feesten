package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 14/06/2016.
 */
public class Event implements Parcelable {
    private String id;
    private String activiteitsId;
    private String titel;
    private String modified;
    private String omschrijving;
    private String datum;
    private String periode;
    private String startuur;
    private String einduur;
    private String tijdstip_sortering;
    private String uitmetvlieg;
    private String inhetgents;
    private String afbeelding;
    private String doventolk;
    private String organisatie;
    private String organisator_id;
    private String gents_initiatief;
    private String videos;
    private String zoekwoorden;
    private String meer_info;
    private String festival;
    private String ghent_selection;
    private String url;
    private String organisatie_website;
    private String gratis;
    private String aparte_prijs_vvk;
    private String prijs;
    private String prijs_omschrijving;
    private String prijs_vvk;
    private String prijs_vvk_omschrijving;
    private String korting;
    private String categorie_id;
    private String categorie_naam;
    private String toegankelijk_rolstoel;
    private String overkoepelende_titel;
    private String overkoepelende_omschrijving;
    private String locatie_id;
    private String locatie;
    private String sublocatie;
    private String straat;
    private String huisnummer;
    private String bus;
    private String postcode;
    private String gemeente;
    private String latitude;
    private String longitude;
    private String dagklapper;

    // onderstaande zijn in json elk een array ==> []
    private String infopunt;
    private String reservatiepunt;

    // dit is een json object in json ==> {}
    private String extra_category;

    public Event() {
        // lege constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActiviteitsId() {
        return activiteitsId;
    }

    public void setActiviteitsId(String activiteitsId) {
        this.activiteitsId = activiteitsId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getStartuur() {
        return startuur;
    }

    public void setStartuur(String startuur) {
        this.startuur = startuur;
    }

    public String getEinduur() {
        return einduur;
    }

    public void setEinduur(String einduur) {
        this.einduur = einduur;
    }

    public String getTijdstip_sortering() {
        return tijdstip_sortering;
    }

    public void setTijdstip_sortering(String tijdstip_sortering) {
        this.tijdstip_sortering = tijdstip_sortering;
    }

    public String getUitmetvlieg() {
        return uitmetvlieg;
    }

    public void setUitmetvlieg(String uitmetvlieg) {
        this.uitmetvlieg = uitmetvlieg;
    }

    public String getInhetgents() {
        return inhetgents;
    }

    public void setInhetgents(String inhetgents) {
        this.inhetgents = inhetgents;
    }

    public String getAfbeelding() {
        return afbeelding;
    }

    public void setAfbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public String getDoventolk() {
        return doventolk;
    }

    public void setDoventolk(String doventolk) {
        this.doventolk = doventolk;
    }

    public String getOrganisatie() {
        return organisatie;
    }

    public void setOrganisatie(String organisatie) {
        this.organisatie = organisatie;
    }

    public String getOrganisator_id() {
        return organisator_id;
    }

    public void setOrganisator_id(String organisator_id) {
        this.organisator_id = organisator_id;
    }

    public String getGents_initiatief() {
        return gents_initiatief;
    }

    public void setGents_initiatief(String gents_initiatief) {
        this.gents_initiatief = gents_initiatief;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getZoekwoorden() {
        return zoekwoorden;
    }

    public void setZoekwoorden(String zoekwoorden) {
        this.zoekwoorden = zoekwoorden;
    }

    public String getMeer_info() {
        return meer_info;
    }

    public void setMeer_info(String meer_info) {
        this.meer_info = meer_info;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public String getGhent_selection() {
        return ghent_selection;
    }

    public void setGhent_selection(String ghent_selection) {
        this.ghent_selection = ghent_selection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrganisatie_website() {
        return organisatie_website;
    }

    public void setOrganisatie_website(String organisatie_website) {
        this.organisatie_website = organisatie_website;
    }

    public String getGratis() {
        return gratis;
    }

    public void setGratis(String gratis) {
        this.gratis = gratis;
    }

    public String getAparte_prijs_vvk() {
        return aparte_prijs_vvk;
    }

    public void setAparte_prijs_vvk(String aparte_prijs_vvk) {
        this.aparte_prijs_vvk = aparte_prijs_vvk;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }

    public String getPrijs_vvk() {
        return prijs_vvk;
    }

    public String getPrijs_omschrijving() {
        return prijs_omschrijving;
    }

    public void setPrijs_omschrijving(String prijs_omschrijving) {
        this.prijs_omschrijving = prijs_omschrijving;
    }

    public void setPrijs_vvk(String prijs_vvk) {
        this.prijs_vvk = prijs_vvk;
    }

    public String getPrijs_vvk_omschrijving() {
        return prijs_vvk_omschrijving;
    }

    public void setPrijs_vvk_omschrijving(String prijs_vvk_omschrijving) {
        this.prijs_vvk_omschrijving = prijs_vvk_omschrijving;
    }

    public String getKorting() {
        return korting;
    }

    public void setKorting(String korting) {
        this.korting = korting;
    }

    public String getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getCategorie_naam() {
        return categorie_naam;
    }

    public void setCategorie_naam(String categorie_naam) {
        this.categorie_naam = categorie_naam;
    }

    public String getToegankelijk_rolstoel() {
        return toegankelijk_rolstoel;
    }

    public void setToegankelijk_rolstoel(String toegankelijk_rolstoel) {
        this.toegankelijk_rolstoel = toegankelijk_rolstoel;
    }

    public String getOverkoepelende_titel() {
        return overkoepelende_titel;
    }

    public void setOverkoepelende_titel(String overkoepelende_titel) {
        this.overkoepelende_titel = overkoepelende_titel;
    }

    public String getOverkoepelende_omschrijving() {
        return overkoepelende_omschrijving;
    }

    public void setOverkoepelende_omschrijving(String overkoepelende_omschrijving) {
        this.overkoepelende_omschrijving = overkoepelende_omschrijving;
    }

    public String getLocatie_id() {
        return locatie_id;
    }

    public void setLocatie_id(String locatie_id) {
        this.locatie_id = locatie_id;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getSublocatie() {
        return sublocatie;
    }

    public void setSublocatie(String sublocatie) {
        this.sublocatie = sublocatie;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDagklapper() {
        return dagklapper;
    }

    public void setDagklapper(String dagklapper) {
        this.dagklapper = dagklapper;
    }

    public String getInfopunt() {
        return infopunt;
    }

    public void setInfopunt(String infopunt) {
        this.infopunt = infopunt;
    }

    public String getReservatiepunt() {
        return reservatiepunt;
    }

    public void setReservatiepunt(String reservatiepunt) {
        this.reservatiepunt = reservatiepunt;
    }

    public String getExtra_category() {
        return extra_category;
    }

    public void setExtra_category(String extra_category) {
        this.extra_category = extra_category;
    }

    public Event(Parcel in) {
        id = in.readString();
        activiteitsId = in.readString();
        titel = in.readString();
        modified = in.readString();
        omschrijving = in.readString();
        datum = in.readString();
        periode = in.readString();
        startuur = in.readString();
        einduur = in.readString();
        tijdstip_sortering = in.readString();
        uitmetvlieg = in.readString();
        inhetgents = in.readString();
        afbeelding = in.readString();
        doventolk = in.readString();
        organisatie = in.readString();
        organisator_id = in.readString();
        gents_initiatief = in.readString();
        videos = in.readString();
        zoekwoorden = in.readString();
        meer_info = in.readString();
        festival = in.readString();
        ghent_selection = in.readString();
        url = in.readString();
        organisatie_website = in.readString();
        gratis = in.readString();
        aparte_prijs_vvk = in.readString();
        prijs = in.readString();
        prijs_omschrijving = in.readString();
        prijs_vvk = in.readString();
        prijs_vvk_omschrijving = in.readString();
        korting = in.readString();
        categorie_id = in.readString();
        categorie_naam = in.readString();
        toegankelijk_rolstoel = in.readString();
        overkoepelende_titel = in.readString();
        overkoepelende_omschrijving = in.readString();
        locatie_id = in.readString();
        locatie = in.readString();
        sublocatie = in.readString();
        straat = in.readString();
        huisnummer = in.readString();
        bus = in.readString();
        postcode = in.readString();
        gemeente = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        dagklapper = in.readString();
    }

    public static final Creator<Event> CREATOR =
            new Creator<Event>() {
                @Override
                public Event createFromParcel(Parcel pc) {
                    return new Event(pc);
                }

                @Override
                public Event[] newArray(int size) {
                    return new Event[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(activiteitsId);
        dest.writeString(titel);
        dest.writeString(modified);
        dest.writeString(omschrijving);
        dest.writeString(datum);
        dest.writeString(periode);
        dest.writeString(startuur);
        dest.writeString(einduur);
        dest.writeString(tijdstip_sortering);
        dest.writeString(uitmetvlieg);
        dest.writeString(inhetgents);
        dest.writeString(afbeelding);
        dest.writeString(doventolk);
        dest.writeString(organisatie);
        dest.writeString(organisator_id);
        dest.writeString(gents_initiatief);
        dest.writeString(videos);
        dest.writeString(zoekwoorden);
        dest.writeString(meer_info);
        dest.writeString(festival);
        dest.writeString(ghent_selection);
        dest.writeString(url);
        dest.writeString(organisatie_website);
        dest.writeString(gratis);
        dest.writeString(aparte_prijs_vvk);
        dest.writeString(prijs);
        dest.writeString(prijs_omschrijving);
        dest.writeString(prijs_vvk);
        dest.writeString(prijs_vvk_omschrijving);
        dest.writeString(korting);
        dest.writeString(categorie_id);
        dest.writeString(categorie_naam);
        dest.writeString(toegankelijk_rolstoel);
        dest.writeString(overkoepelende_titel);
        dest.writeString(overkoepelende_omschrijving);
        dest.writeString(locatie_id);
        dest.writeString(locatie);
        dest.writeString(sublocatie);
        dest.writeString(straat);
        dest.writeString(huisnummer);
        dest.writeString(bus);
        dest.writeString(postcode);
        dest.writeString(gemeente);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(dagklapper);
    }
}
