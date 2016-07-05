package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 14/06/2016.
 */
public class Locatie implements Parcelable {
    private String locatieID;
    private String locatieNaam;

    public Locatie() {
        // lege constructor
    }

    public String getLocatieID() {
        return locatieID;
    }

    public void setLocatieID(String locatieID) {
        this.locatieID = locatieID;
    }

    public String getLocatieNaam() {
        return locatieNaam;
    }

    public void setLocatieNaam(String locatieNaam) {
        this.locatieNaam = locatieNaam;
    }

    public Locatie(Parcel in) {
        locatieID = in.readString();
        locatieNaam = in.readString();
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
        dest.writeString(locatieNaam);
    }
}
