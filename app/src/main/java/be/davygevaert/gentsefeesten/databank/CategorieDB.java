package be.davygevaert.gentsefeesten.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.constanten.CategorieTabel;
import be.davygevaert.gentsefeesten.model.Categorie;

/**
 * Created by Davy on 16/06/2016.
 */
public class CategorieDB extends SchemaHelper {

    private SQLiteDatabase sd;

    public CategorieDB(Context context) {
        super(context);
    }

    public long addCategorie(Categorie categorie) {
        // AANMAAK VAN EEN CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();

        cv.put(CategorieTabel.CATEGORIE_ID, categorie.getCategorieID());
        cv.put(CategorieTabel.CATEGORIE_TITEL, categorie.getCategorieTitel());

        // BEKOMEN VAN EEN SCHRIJFBARE DATABANK EN VERVOLGENS DE RECORD TOEVOEGEN
        sd = this.getWritableDatabase();

        long result = sd.insert(CategorieTabel.TABEL_NAAM, CategorieTabel.CATEGORIE_TITEL, cv);
        return result;
    }

    public ArrayList<Categorie> getCategorieen() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Categorie> categorieArrayList = null;
        try{
            categorieArrayList = new ArrayList<Categorie>();

            String QUERY = "SELECT * FROM " + CategorieTabel.TABEL_NAAM +
                    " ORDER BY " + CategorieTabel.CATEGORIE_TITEL + " ASC";

            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Categorie categorie = new Categorie();
                    categorie.setCategorieID(cursor.getString(0));
                    categorie.setCategorieTitel(cursor.getString(1));
                    categorieArrayList.add(categorie);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return categorieArrayList;
    }

    public int getCategorieCount() {
        return getCategorieen().size();
    }

    // een cursor met één Data op basis van een timestamp
    public Cursor getCategorie(long categorieID) {
        String[] resultaatKolom = { CategorieTabel.CATEGORIE_ID, CategorieTabel.CATEGORIE_TITEL };
        sd = this.getReadableDatabase();
        return sd.query(true, CategorieTabel.TABEL_NAAM, resultaatKolom, CategorieTabel.CATEGORIE_ID + "=" + categorieID,
                null, null, null, null, null, null);
    }
}
