package be.davygevaert.gentsefeesten.databank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

import be.davygevaert.gentsefeesten.constanten.CategorieTabel;
import be.davygevaert.gentsefeesten.constanten.EventTabel;
import be.davygevaert.gentsefeesten.constanten.LocatieTabel;
import be.davygevaert.gentsefeesten.constanten.OrganisatorTabel;

/**
 * Created by Davy on 16/06/2016.
 */
public class SchemaHelper extends SQLiteOpenHelper {

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "GentseFeesten";

    public SchemaHelper(Context context) {
        // originele super indien apk wordt gereleased en dan commenteer je de tweede super met /* */
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

        // aanmaken CategorieTabel
        db.execSQL("CREATE TABLE "
                        + CategorieTabel.TABEL_NAAM
                        + " ( " + CategorieTabel.CATEGORIE_ID + " TEXT, "
                        + CategorieTabel.CATEGORIE_TITEL + " TEXT"
                        + ");"
        );

        // aanmaken LocatieTabel
        db.execSQL("CREATE TABLE "
                        + LocatieTabel.TABEL_NAAM
                        + " ( " + LocatieTabel.LOCATIE_ID + " TEXT, "
                        + LocatieTabel.LOCATIE_NAAM + " TEXT, "
                        + LocatieTabel.LOCATIE_STRAAT + " TEXT, "
                        + LocatieTabel.LOCATIE_POSTCODE + " TEXT, "
                        + LocatieTabel.LOCATIE_GEMEENTE + " TEXT, "
                        + LocatieTabel.ROLSTOEL_TOEGANKELIJKHEID + " TEXT"
                        + ");"
        );

        // aanmaken OrganisatorTabel
        db.execSQL("CREATE TABLE "
                        + OrganisatorTabel.TABEL_NAAM
                        + " ( " + OrganisatorTabel.ORGANISATIE_ID + " TEXT, "
                        + OrganisatorTabel.ORGANISATIE_NAAM + " TEXT, "
                        + OrganisatorTabel.ORGANISATIE_STRAAT + " TEXT, "
                        + OrganisatorTabel.ORGANISATIE_POSTCODE + " TEXT, "
                        + OrganisatorTabel.ORGANISATIE_GEMEENTE + " TEXT"
                        + ");"
        );

        // aanmaken EventTabel
        db.execSQL("CREATE TABLE "
                + EventTabel.TABEL_NAAM

                + " ( " + EventTabel.EVENT_ID + " TEXT, "
                + EventTabel.EVENT_NAAM + " TEXT, "
                + EventTabel.EVENT_TYPE + " TEXT, "
                + EventTabel.EVENT_CONTACTPOINT_ID + " TEXT, "
                + EventTabel.EVENT_CONTRIBUTOR_TYPE + " TEXT, "
                + EventTabel.EVENT_CONTRIBUTOR_NAME + " TEXT, "
                + EventTabel.EVENT_BESCHRIJVING + " TEXT, "
                + EventTabel.EVENT_AFBEELDING_URL + " TEXT, "
                + EventTabel.EVENT_AFBEELDING_THUMBNAIL + " TEXT, "
                + EventTabel.EVENT_AFBEELDING_TITEL + " TEXT, "

                + EventTabel.EVENT_TAAL + " TEXT, "
                + EventTabel.EVENT_IS_ACCESSIBLE_FOR_FREE + " TEXT, "
                + EventTabel.EVENT_IS_PART_OF + " TEXT, "
                + EventTabel.EVENT_ROLSTOELTOEGANKELIJKHEID + " TEXT, "
                + EventTabel.EVENT_KERNWOORDEN + " TEXT, "
                + EventTabel.EVENT_LOCATIE_ID + " TEXT, "
                + EventTabel.EVENT_STARTDATUM_LONG + " TEXT, "
                + EventTabel.EVENT_STARTDATUM_SHORT + " TEXT, "
                + EventTabel.EVENT_STARTUUR + " TEXT, "
                + EventTabel.EVENT_EINDUUR + " TEXT, "
                + EventTabel.EVENT_ORGANISATOR_ID + " TEXT, "

                + EventTabel.EVENT_CATEGORIE_ID + " TEXT, "
                + EventTabel.EVENT_WEBSITE_URL + " TEXT, "
                + EventTabel.EVENT_VIDEO_URL + " TEXT, "
                + EventTabel.EVENT_VIDEO_THUMBNAIL + " TEXT, "
                + EventTabel.EVENT_VIDEO_ONDERSCHRIFT + " TEXT, "
                + EventTabel.EVENT_PRIJS + " TEXT, "
                + EventTabel.EVENT_WISSELKOERS + " TEXT, "
                + EventTabel.EVENT_PRIJS_OMSCHRIJVING + " TEXT, "
                + EventTabel.EVENT_VOORVERKOOPPRIJS + " TEXT, "
                + EventTabel.EVENT_VERKRIJGBAARHEID + " TEXT, "
                + EventTabel.EVENT_KORTING + " TEXT"
                + ");" );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ZAL DE BESTAANDE TABEL VERWIJDEREN WANNEER ER EEN UPGRADE IS
        db.execSQL("DROP TABLE IF EXISTS " + LocatieTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS " + CategorieTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS " + EventTabel.TABEL_NAAM);
        db.execSQL("DROP TABLE IF EXISTS " + OrganisatorTabel.TABEL_NAAM);

        // AANMAAK VAN EEN NIEUWE INSTANTIE VAN HET SCHERM
        onCreate(db);
    }
}
