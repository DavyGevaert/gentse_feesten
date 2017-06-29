package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 12/06/2017.
 */
public class Organisator implements Parcelable {
    private String organisatieId;
    private String organisatieNaam;
    private String organisatieStraat;
    private String organisatiePostcode;
    private String organisatieGemeente;

    public Organisator() {
        // lege constructor
    }

    public String getOrganisatieId() {
        return organisatieId;
    }

    public void setOrganisatieId(String organisatieId) {
        this.organisatieId = organisatieId;
    }

    public String getOrganisatieNaam() {
        return organisatieNaam;
    }

    public void setOrganisatieNaam(String organisatieNaam) { this.organisatieNaam = organisatieNaam; }

    public String getOrganisatieStraat() {
        return organisatieStraat;
    }

    public void setOrganisatieStraat(String organisatieStraat) { this.organisatieStraat = organisatieStraat; }

    public String getOrganisatiePostcode() {
        return organisatiePostcode;
    }

    public void setOrganisatiePostcode(String organisatiePostcode) { this.organisatiePostcode = organisatiePostcode; }

    public String getOrganisatieGemeente() {
        return organisatieGemeente;
    }

    public void setOrganisatieGemeente(String organisatieGemeente) { this.organisatieGemeente = organisatieGemeente; }

    public Organisator(Parcel in) {
        organisatieId = in.readString();
        organisatieNaam = in.readString();
        organisatieStraat = in.readString();
        organisatiePostcode = in.readString();
        organisatieGemeente = in.readString();
    }

    public static final Creator<Organisator> CREATOR =
            new Creator<Organisator>() {
                @Override
                public Organisator createFromParcel(Parcel pc) {
                    return new Organisator(pc);
                }

                @Override
                public Organisator[] newArray(int size) {
                    return new Organisator[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(organisatieId);
        dest.writeString(organisatieNaam);
        dest.writeString(organisatieStraat);
        dest.writeString(organisatiePostcode);
        dest.writeString(organisatieGemeente);
    }
}
