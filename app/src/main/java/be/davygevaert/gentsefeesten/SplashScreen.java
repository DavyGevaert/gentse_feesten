package be.davygevaert.gentsefeesten;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import be.davygevaert.gentsefeesten.asynctask.VerkrijgCategorieenTask;
import be.davygevaert.gentsefeesten.asynctask.VerkrijgDataTask;
import be.davygevaert.gentsefeesten.asynctask.VerkrijgEventsTask;
import be.davygevaert.gentsefeesten.asynctask.VerkrijgLocatiesTask;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.databank.SchemaHelper;


public class SplashScreen extends Activity {

    private static final String TAG = SplashScreen.class.getSimpleName();

    private final int SPLASH_DISPLAY_LENGTH = 20000;

    private Intent intent;
    private Context context;
    private ProgressBar progressBar;

    private static boolean activity_is_running = false;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = this;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // database verwijderen bij opstart
        SchemaHelper handler = new SchemaHelper(getApplicationContext());

        // aanmaken intent en specifieer welke volgende Activity er moet worden geladen
        intent = new Intent(SplashScreen.this, DataActivity.class);

        // initialisatie sharedPreferences en boolean
        final SharedPreferences initialPref = getSharedPreferences("INITIAL", 0);
        boolean firsttimer = initialPref.getBoolean("INITIAL", false);

        // via SharedPreferences de database aanmaken als deze applicatie voor de eerste keer opstart met gebruik van deze boolean
        if (!firsttimer){
            //create database here

            // Handler om de DataActivity.class te starten en dit SplashScreen venster af te sluiten
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    // nakijken indien database tabellen geen inhoud bevatten, indien geen gegevens maak dan de database en tabellen op aan de hand van de gegevens uit de Assets folder
                    // if (dataDB.getDataCount() == 0 || categorieDB.getCategorieCount() == 0 || locatieDB.getLocatieCount() == 0 || eventDB.getEventCount() == 0) {

                        // plaats hier asynctasks
                        // en execute met context als parameter indien assets folder bereikbaar moet zijn
                        new VerkrijgDataTask(context).execute(Constants.GENTSE_FEESTEN_DATA);
                        new VerkrijgCategorieenTask(context).execute(Constants.GENTSE_FEESTEN_CATEGORIEEN);
                        new VerkrijgLocatiesTask(context).execute(Constants.GENTSE_FEESTEN_LOCATIES);
                        new VerkrijgEventsTask(context).execute(Constants.GENTSE_FEESTEN_EVENTS);

                        //get boolean preference to true
                        SharedPreferences.Editor editorPref = initialPref.edit();
                        editorPref.putBoolean("INITIAL", true);
                        editorPref.commit();

                        // ongeacht ingestelde duur SPLASH_DISPLAY_LENGTH
                        // indien asynctasks verwerkt zijn op de achtergrond, direct daarna startActivity met intent
                        SplashScreen.this.startActivity(intent);
                        // direct afsluiten SplashScreen zodat als gebruiker in DataActivity op terugtoets smartphone klikt/duwt,
                        // hij/zij teruggaat naar bureaublad smartphone
                        SplashScreen.this.finish();
                    }
            }, SPLASH_DISPLAY_LENGTH);

        } else {

            // indien de applicatie detecteert vanuit de opgeslagen boolean in sharedPreferences dat de applicatie al een eerste keer heeft gedraaid, dan start direct de applicatie
            // met het begrip dat de database al is aangemaakt

            SplashScreen.this.startActivity(intent);
            // direct afsluiten SplashScreen zodat als gebruiker in DataActivity op terugtoets smartphone klikt/duwt,
            // hij/zij teruggaat naar bureaublad smartphone
            SplashScreen.this.finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        activity_is_running = true;

        if (activity_is_running){
            myCountDownTimer = new MyCountDownTimer(20000, 1000);
            myCountDownTimer.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        activity_is_running = false;
        myCountDownTimer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished/1000);

            progressBar.setProgress(progressBar.getMax() -progress);
        }

        @Override
        public void onFinish() {
            finish();
        }
    }
}
