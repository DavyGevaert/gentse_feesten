package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 14/06/2016.
 */
public class Locatie implements Parcelable {
    private String locatieID;
    private String naam;
    private String straat;
    private String postcode;
    private String gemeente;
    private String rolstoel_toegankelijkheid;

    public Locatie() {
        // lege constructor
    }

    public String getLocatieID() {
        return locatieID;
    }

    public void setLocatieID(String locatieID) {
        this.locatieID = locatieID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
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

    public String getRolstoel_toegankelijkheid() {
        return rolstoel_toegankelijkheid;
    }

    public void setRolstoel_toegankelijkheid(String rolstoel_toegankelijkheid) {
        this.rolstoel_toegankelijkheid = rolstoel_toegankelijkheid;
    }

    public Locatie(Parcel in) {
        locatieID = in.readString();
        naam = in.readString();
        straat = in.readString();
        postcode = in.readString();
        gemeente = in.readString();
        rolstoel_toegankelijkheid = in.readString();
    }

    public static final Parcelable.Creator<Locatie> CREATOR =
            new Parcelable.Creator<Locatie>() {
                @Override
                public Locatie createFromParcel(Parcel pc) {
                    return new Locatie(pc);
                }

                @Override
                public Locatie[] newArray(int size) {
                    return new Locatie[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locatieID);
        dest.writeString(naam);
        dest.writeString(straat);
        dest.writeString(postcode);
        dest.writeString(gemeente);
        dest.writeString(rolstoel_toegankelijkheid);
    }
}
