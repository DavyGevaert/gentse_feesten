package be.davygevaert.gentsefeesten.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.constanten.DataTabel;
import be.davygevaert.gentsefeesten.model.Data;

/**
 * Created by Davy on 16/06/2016.
 */
public class DataDB extends SchemaHelper {

    private SQLiteDatabase sd;

    public DataDB(Context context) {
        super(context);
    }

    public long addData(Data data) {
        // AANMAAK VAN EEN CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();

        cv.put(DataTabel.TIMESTAMP, data.getTimestamp());
        cv.put(DataTabel.DAY, data.getDay());

        // BEKOMEN VAN EEN SCHRIJFBARE DATABANK EN VERVOLGENS DE RECORD TOEVOEGEN
        sd = this.getWritableDatabase();

        long result = sd.insert(DataTabel.TABEL_NAAM, DataTabel.DAY, cv);
        return result;
    }

    public ArrayList<Data> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Data> dataArrayLijst = null;
        try{
            dataArrayLijst = new ArrayList<Data>();
            String QUERY = "SELECT * FROM "+ DataTabel.TABEL_NAAM;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Data data = new Data();
                    data.setTimestamp(cursor.getString(0));
                    data.setDay(cursor.getString(1));
                    dataArrayLijst.add(data);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return dataArrayLijst;
    }

    public int getDataCount() {
        return getData().size();
    }

    // een cursor met één Data op basis van een timestamp
    public Cursor getSingleData(long timestamp) {
        String[] resultaatKolom = { DataTabel.TIMESTAMP, DataTabel.DAY};
        sd = this.getReadableDatabase();
        return sd.query(true, DataTabel.TABEL_NAAM, resultaatKolom, DataTabel.TIMESTAMP + "=" + timestamp,
                null, null, null, null, null, null);
    }
}
