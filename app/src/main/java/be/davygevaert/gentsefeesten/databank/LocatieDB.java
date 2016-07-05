package be.davygevaert.gentsefeesten.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.constanten.LocatieTabel;
import be.davygevaert.gentsefeesten.model.Locatie;

/**
 * Created by Davy on 16/06/2016.
 */
public class LocatieDB extends SchemaHelper {

    private SQLiteDatabase sd;

    public LocatieDB(Context context) {
        super(context);
    }

    public long addLocatie(Locatie locatie) {
        // AANMAAK VAN EEN CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();

        cv.put(LocatieTabel.LOCATIE_ID, locatie.getLocatieID());
        cv.put(LocatieTabel.LOCATIE_NAAM, locatie.getLocatieNaam());

        // BEKOMEN VAN EEN SCHRIJFBARE DATABANK EN VERVOLGENS DE RECORD TOEVOEGEN
        sd = this.getWritableDatabase();

        long result = sd.insert(LocatieTabel.TABEL_NAAM, LocatieTabel.LOCATIE_NAAM, cv);
        return result;
    }

    public ArrayList<Locatie> getLocaties() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Locatie> locatiesArrayList = null;
        try{
            locatiesArrayList = new ArrayList<Locatie>();
            String QUERY = "SELECT * FROM "+ LocatieTabel.TABEL_NAAM;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Locatie locatie = new Locatie();
                    locatie.setLocatieID(cursor.getString(0));
                    locatie.setLocatieNaam(cursor.getString(1));
                    locatiesArrayList.add(locatie);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return locatiesArrayList;
    }

    public int getLocatieCount() {
        return getLocaties().size();
    }

    // een cursor met één Locatie op basis van een locatieID
    public Cursor getLocatie(long locatieID) {
        String[] resultaatKolom = { LocatieTabel.LOCATIE_ID, LocatieTabel.LOCATIE_NAAM};
        sd = this.getReadableDatabase();
        return sd.query(true, LocatieTabel.TABEL_NAAM, resultaatKolom, LocatieTabel.LOCATIE_ID + "=" + locatieID,
                            null, null, null, null, null, null);
    }


}
