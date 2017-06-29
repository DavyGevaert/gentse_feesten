package be.davygevaert.gentsefeesten.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.constanten.OrganisatorTabel;
import be.davygevaert.gentsefeesten.model.Organisator;

/**
 * Created by Davy on 12/06/2017.
 */
public class OrganisatorDB extends SchemaHelper {

    private SQLiteDatabase sd;

    public OrganisatorDB(Context context) {
        super(context);
    }

    public long addDOrganisator(Organisator organisator) {
        // AANMAAK VAN EEN CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();

        cv.put(OrganisatorTabel.ORGANISATIE_ID, organisator.getOrganisatieId());
        cv.put(OrganisatorTabel.ORGANISATIE_NAAM, organisator.getOrganisatieNaam());
        cv.put(OrganisatorTabel.ORGANISATIE_STRAAT, organisator.getOrganisatieStraat());
        cv.put(OrganisatorTabel.ORGANISATIE_POSTCODE, organisator.getOrganisatiePostcode());
        cv.put(OrganisatorTabel.ORGANISATIE_GEMEENTE, organisator.getOrganisatieGemeente());

        // BEKOMEN VAN EEN SCHRIJFBARE DATABANK EN VERVOLGENS DE RECORD TOEVOEGEN
        sd = this.getWritableDatabase();

        long result = sd.insert(OrganisatorTabel.TABEL_NAAM, OrganisatorTabel.ORGANISATIE_NAAM, cv);
        return result;
    }

    public ArrayList<Organisator> getOrganisatoren() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Organisator> organisatoren_ArrayLijst = null;
        try{
            organisatoren_ArrayLijst = new ArrayList<Organisator>();
            String QUERY = "SELECT * FROM " + OrganisatorTabel.TABEL_NAAM +
                    " ORDER BY " + OrganisatorTabel.ORGANISATIE_NAAM + " ASC";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Organisator organisator = new Organisator();

                    organisator.setOrganisatieId(cursor.getString(0));
                    organisator.setOrganisatieNaam(cursor.getString(1));
                    organisator.setOrganisatieStraat(cursor.getString(2));
                    organisator.setOrganisatiePostcode(cursor.getString(3));
                    organisator.setOrganisatieGemeente(cursor.getString(4));

                    organisatoren_ArrayLijst.add(organisator);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return organisatoren_ArrayLijst;
    }

    public int getOrganisatorenCount() {
        return getOrganisatoren().size();
    }

    // een cursor met één Data op basis van een timestamp
    public Cursor getSingleData(long id) {
        /*
        String[] resultaatKolom = { OrganisatorTabel.ORGANISATIE_ID, OrganisatorTabel.ORGANISATIE_NAAM};
        sd = this.getReadableDatabase();
        return sd.query(true, OrganisatorTabel.TABEL_NAAM, resultaatKolom, OrganisatorTabel.ORGANISATIE_ID + "=" + id,
                null, null, null, null, null, null);
                */
        return null;
    }
}