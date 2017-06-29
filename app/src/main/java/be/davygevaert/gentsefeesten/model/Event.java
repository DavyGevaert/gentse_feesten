package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 14/06/2016.
 */
public class Event implements Parcelable {
    private String id;
    private String naam;
    private String type;
    private String contactPuntId;
    private String bijdragerType;      // (bijdrager = contributor)
    private String bijdragerNaam;
    private String beschrijving;
    private String afbeeldingUrl;
    private String afbeeldingThumbNail;
    private String afbeeldingTitel;
    private String taal;
    private String isGratisToegang;
    private String maaktDeelUitVan;
    private String rolstoelToegankelijkheid;
    private String kernwoorden;
    private String locatieId;
    private String startDatumLong;
    private String startDatumShort;
    private String startUur;
    private String eindUur;
    private String organisatorId;
    private String categorieId;
    private String websiteUrl;
    private String videoUrl;            // embedUrl
    private String videoThumbnail;      // thumbnail
    private String videoOnderschrift;   // caption

    // "video":[{"embedUrl":"https://www.youtube.com/embed/EOTNuIA4uKI"
    // "thumbnail":"http://img.youtube.com/vi/EOTNuIA4uKI/maxresdefault.jpg",
    // "caption":"Low Land Home - Underspoken (Official Music Video)"}]

    private String prijs;
    private String wisselkoers;
    private String prijs_omschrijving;
    private String voorverkoopPrijs;
    private String verkrijgbaarheid;
    private String korting;
    private Categorie categorie;
    private Locatie locatie;
    private Organisator organisator;

    public Event() {
        // lege constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactPuntId() {
        return contactPuntId;
    }

    public void setContactPuntId(String contactPuntId) {
        this.contactPuntId = contactPuntId;
    }

    public String getBijdragerType() {
        return bijdragerType;
    }

    public void setBijdragerType(String bijdragerType) {
        this.bijdragerType = bijdragerType;
    }

    public String getBijdragerNaam() {
        return bijdragerNaam;
    }

    public void setBijdragerNaam(String bijdragerNaam) {
        this.bijdragerNaam = bijdragerNaam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getAfbeeldingUrl() {
        return afbeeldingUrl;
    }

    public void setAfbeeldingUrl(String afbeeldingUrl) {
        this.afbeeldingUrl = afbeeldingUrl;
    }

    public String getAfbeeldingThumbNail() {
        return afbeeldingThumbNail;
    }

    public void setAfbeeldingThumbNail(String afbeeldingThumbNail) {
        this.afbeeldingThumbNail = afbeeldingThumbNail;
    }

    public String getAfbeeldingTitel() {
        return afbeeldingTitel;
    }

    public void setAfbeeldingTitel(String afbeeldingTitel) {
        this.afbeeldingTitel = afbeeldingTitel;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public String getIsGratisToegang() {
        return isGratisToegang;
    }

    public void setIsGratisToegang(String isGratisToegang) {
        this.isGratisToegang = isGratisToegang;
    }

    public String getMaaktDeelUitVan() {
        return maaktDeelUitVan;
    }

    public void setMaaktDeelUitVan(String maaktDeelUitVan) {
        this.maaktDeelUitVan = maaktDeelUitVan;
    }

    public String getRolstoelToegankelijkheid() {
        return rolstoelToegankelijkheid;
    }

    public void setRolstoelToegankelijkheid(String rolstoelToegankelijkheid) {
        this.rolstoelToegankelijkheid = rolstoelToegankelijkheid;
    }

    public String getKernwoorden() {
        return kernwoorden;
    }

    public void setKernwoorden(String kernwoorden) {
        this.kernwoorden = kernwoorden;
    }

    public String getLocatieId() {
        return locatieId;
    }

    public void setLocatieId(String locatieId) {
        this.locatieId = locatieId;
    }

    public String getStartDatumLong() {
        return startDatumLong;
    }

    public void setStartDatumLong(String startDatumLong) {
        this.startDatumLong = startDatumLong;
    }

    public String getStartDatumShort() {
        return startDatumShort;
    }

    public void setStartDatumShort(String startDatumShort) {
        this.startDatumShort = startDatumShort;
    }

    public String getStartUur() {
        return startUur;
    }

    public void setStartUur(String startUur) {
        this.startUur = startUur;
    }

    public String getEindUur() {
        return eindUur;
    }

    public void setEindUur(String eindUur) {
        this.eindUur = eindUur;
    }

    public String getOrganisatorId() {
        return organisatorId;
    }

    public void setOrganisatorId(String organisatorId) {
        this.organisatorId = organisatorId;
    }

    public String getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = categorieId;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoOnderschrift() {
        return videoOnderschrift;
    }

    public void setVideoOnderschrift(String videoOnderschrift) {
        this.videoOnderschrift = videoOnderschrift;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }

    public String getWisselkoers() {
        return wisselkoers;
    }

    public void setWisselkoers(String wisselkoers) {
        this.wisselkoers = wisselkoers;
    }

    public String getPrijs_omschrijving() {
        return prijs_omschrijving;
    }

    public void setPrijs_omschrijving(String prijs_omschrijving) {
        this.prijs_omschrijving = prijs_omschrijving;
    }

    public String getVoorverkoopPrijs() {
        return voorverkoopPrijs;
    }

    public void setVoorverkoopPrijs(String voorverkoopPrijs) {
        this.voorverkoopPrijs = voorverkoopPrijs;
    }

    public String getVerkrijgbaarheid() {
        return verkrijgbaarheid;
    }

    public void setVerkrijgbaarheid(String verkrijgbaarheid) {
        this.verkrijgbaarheid = verkrijgbaarheid;
    }

    public String getKorting() {
        return korting;
    }

    public void setKorting(String korting) {
        this.korting = korting;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public Organisator getOrganisator() {
        return organisator;
    }

    public void setOrganisator(Organisator organisator) {
        this.organisator = organisator;
    }

    public Event(Parcel in) {
        id = in.readString();
        naam = in.readString();
        type = in.readString();
        contactPuntId = in.readString();
        bijdragerType = in.readString();
        bijdragerNaam = in.readString();
        beschrijving = in.readString();
        afbeeldingUrl = in.readString();
        afbeeldingThumbNail = in.readString();
        afbeeldingTitel = in.readString();
        taal = in.readString();
        isGratisToegang = in.readString();
        maaktDeelUitVan = in.readString();
        rolstoelToegankelijkheid = in.readString();
        kernwoorden = in.readString();
        locatieId = in.readString();
        startDatumLong = in.readString();
        startDatumShort = in.readString();
        startUur = in.readString();
        eindUur = in.readString();
        organisatorId = in.readString();
        categorieId = in.readString();
        websiteUrl = in.readString();
        videoUrl = in.readString();
        videoThumbnail = in.readString();
        videoOnderschrift = in.readString();
        prijs = in.readString();
        wisselkoers = in.readString();
        prijs_omschrijving = in.readString();
        voorverkoopPrijs = in.readString();
        verkrijgbaarheid = in.readString();
        korting = in.readString();
        categorie = in.readParcelable(Categorie.class.getClassLoader());
        locatie = in.readParcelable(Locatie.class.getClassLoader());
        organisator = in.readParcelable(Organisator.class.getClassLoader());
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
        dest.writeString(naam);
        dest.writeString(type);
        dest.writeString(contactPuntId);
        dest.writeString(bijdragerType);
        dest.writeString(bijdragerNaam);
        dest.writeString(beschrijving);
        dest.writeString(afbeeldingUrl);
        dest.writeString(afbeeldingThumbNail);
        dest.writeString(afbeeldingTitel);
        dest.writeString(taal);
        dest.writeString(isGratisToegang);
        dest.writeString(maaktDeelUitVan);
        dest.writeString(rolstoelToegankelijkheid);
        dest.writeString(kernwoorden);
        dest.writeString(locatieId);
        dest.writeString(startDatumLong);
        dest.writeString(startDatumShort);
        dest.writeString(startUur);
        dest.writeString(eindUur);
        dest.writeString(organisatorId);
        dest.writeString(categorieId);
        dest.writeString(websiteUrl);
        dest.writeString(videoUrl);
        dest.writeString(videoThumbnail);
        dest.writeString(videoOnderschrift);
        dest.writeString(prijs);
        dest.writeString(wisselkoers);
        dest.writeString(prijs_omschrijving);
        dest.writeString(voorverkoopPrijs);
        dest.writeString(verkrijgbaarheid);
        dest.writeString(korting);
        dest.writeParcelable(categorie, flags);
        dest.writeParcelable(locatie, flags);
        dest.writeParcelable(organisator, flags);
    }
}
