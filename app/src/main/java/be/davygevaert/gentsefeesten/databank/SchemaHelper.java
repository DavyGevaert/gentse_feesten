package be.davygevaert.gentsefeesten.databank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

import be.davygevaert.gentsefeesten.constanten.CategorieTabel;
import be.davygevaert.gentsefeesten.constanten.DataTabel;
import be.davygevaert.gentsefeesten.constanten.EventTabel;
import be.davygevaert.gentsefeesten.constanten.LocatieTabel;

/**
 * Created by Davy on 16/06/2016.
 */
public class SchemaHelper extends SQLiteOpenHelper {

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "GentseFeesten";

    public SchemaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        /* onderstaande voor testdoeleinden mbv programma sqliteadmin
           http://sqliteadmin.orbmu2k.de/
           hier wordt de databank opgeslagen in een mapje GENTSEFEESTENDB op je sd kaart */
        /*
        super(context, Environment.getExternalStorageDirectory()
                + File.separator + "GENTSEFEESTENDB"
                + File.separator + DATABASE_NAME
                , null,DATABASE_VERSION);
        */

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // aanmaken DataTabel
        db.execSQL("CREATE TABLE "
                        + DataTabel.TABEL_NAAM
                        + " ( " + DataTabel.TIMESTAMP
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + DataTabel.DAY + " TEXT"
                        + ");"
        );

        // aanmaken LocatieTabel
        db.execSQL("CREATE TABLE "
                        + LocatieTabel.TABEL_NAAM
                        + " ( " + LocatieTabel.LOCATIE_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + LocatieTabel.LOCATIE_NAAM + " TEXT"
                        + ");"
        );

        // aanmaken CategorieTabel
        db.execSQL("CREATE TABLE "
                        + CategorieTabel.TABEL_NAAM
                        + " ( " + CategorieTabel.CATEGORIE_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + CategorieTabel.CATEGORIE_TITEL + " TEXT"
                        + ");"
        );

        // aanmaken EventTabel
        db.execSQL("CREATE TABLE "
                        + EventTabel.TABEL_NAAM

                        + " ( " + EventTabel.ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                        + EventTabel.ACTIVITEIT_ID + " TEXT,"           // komma niet vergeten bij meerdere kolommen !!!
                        + EventTabel.TITEL + " TEXT,"
                        + EventTabel.MODIFIED+ " TEXT,"
                        + EventTabel.OMSCHRIJVING + " TEXT,"
                        + EventTabel.DATUM + " TEXT,"
                        + EventTabel.PERIODE + " TEXT,"
                        + EventTabel.STARTUUR + " TEXT,"
                        + EventTabel.EINDUUR + " TEXT,"
                        + EventTabel.TIJDSTIP_SORTERING + " TEXT,"

                        + EventTabel.UIT_MET_VLIEG + " TEXT,"
                        + EventTabel.IN_HET_GENTS + " TEXT,"
                        + EventTabel.AFBEELDING + " TEXT,"
                        + EventTabel.DOVENTOLK + " TEXT,"
                        + EventTabel.ORGANISATIE + " TEXT,"
                        + EventTabel.ORGANISATOR_ID + " TEXT,"
                        + EventTabel.GENTS_INITIATIEF + " TEXT,"
                        + EventTabel.VIDEOS + " TEXT,"
                        + EventTabel.ZOEKWOORDEN + " TEXT,"
                        + EventTabel.MEER_INFO + " TEXT,"

                        + EventTabel.FESTIVAL + " TEXT,"
                        + EventTabel.GHENT_SELECTION + " TEXT,"
                        + EventTabel.URL + " TEXT,"
                        + EventTabel.ORGANISATIE_WEBSITE + " TEXT,"
                        + EventTabel.GRATIS + " TEXT,"
                        + EventTabel.APARTE_PRIJS_VVK + " TEXT,"
                        + EventTabel.PRIJS + " TEXT,"
                        + EventTabel.PRIJS_OMSCHRIJVING + " TEXT,"
                        + EventTabel.PRIJS_VVK + " TEXT,"
                        + EventTabel.PRIJS_VVK_OMSCHRIJVING + " TEXT,"

                        + EventTabel.KORTING + " TEXT,"
                        + EventTabel.CATEGORIE_ID + " TEXT,"
                        + EventTabel.CATEGORIE_TITEL + " TEXT,"
                        + EventTabel.TOEGANKELIJK_ROLSTOEL + " TEXT,"
                        + EventTabel.OVERKOEPELENDE_TITEL + " TEXT,"
                        + EventTabel.OVERKOEPELENDE_OMSCHRIJVING + " TEXT,"
                        + EventTabel.LOCATIE_ID + " TEXT,"
                        + EventTabel.LOCATIE_NAAM + " TEXT,"
                        + EventTabel.SUBLOCATIE + " TEXT,"
                        + EventTabel.STRAAT + " TEXT,"

                        + EventTabel.HUISNUMMER + " TEXT,"
                        + EventTabel.BUS + " TEXT,"
                        + EventTabel.POSTCODE + " TEXT,"
                        + EventTabel.GEMEENTE + " TEXT,"
                        + EventTabel.LATITUDE + " TEXT,"
                        + EventTabel.LONGITUDE + " TEXT,"
                        + EventTabel.DAGKLAPPER + " TEXT"
                        + ");"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ZAL DE BESTAANDE TABEL VERWIJDEREN WANNEER ER EEN UPGRADE IS
        db.execSQL("DROP TABLE IF EXISTS " + DataTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS " + LocatieTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS " + CategorieTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS " + EventTabel.TABEL_NAAM);

        // AANMAAK VAN EEN NIEUWE INSTANTIE VAN HET SCHERM
        onCreate(db);
    }
}
