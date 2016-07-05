package be.davygevaert.gentsefeesten.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Davy on 14/06/2016.
 */
public class Categorie implements Parcelable {
    private String categorieID;
    private String categorieTitel;

    public Categorie() {
        // lege constructor
    }

    public String getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(String categorieID) {
        this.categorieID = categorieID;
    }

    public String getCategorieTitel() {
        return categorieTitel;
    }

    public void setCategorieTitel(String categorieTitel) {
        this.categorieTitel = categorieTitel;
    }

    public Categorie(Parcel in) {
        categorieID = in.readString();
        categorieTitel = in.readString();
    }

    public static final Parcelable.Creator<Categorie> CREATOR =
            new Parcelable.Creator<Categorie>() {
                @Override
                public Categorie createFromParcel(Parcel pc) {
                    return new Categorie(pc);
                }

                @Override
                public Categorie[] newArray(int size) {
                    return new Categorie[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categorieID);
        dest.writeString(categorieTitel);
    }
}
