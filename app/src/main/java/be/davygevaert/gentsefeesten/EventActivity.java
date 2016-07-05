package be.davygevaert.gentsefeesten;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.List;

import be.davygevaert.gentsefeesten.adapter.EventRecyclerAdapter;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.databank.EventDB;
import be.davygevaert.gentsefeesten.fragment.NavigationDrawerFragment;
import be.davygevaert.gentsefeesten.model.Categorie;
import be.davygevaert.gentsefeesten.model.Data;
import be.davygevaert.gentsefeesten.model.Event;
import be.davygevaert.gentsefeesten.model.Locatie;
import be.davygevaert.gentsefeesten.tools.Animation;
import be.davygevaert.gentsefeesten.tools.Tools;


public class EventActivity extends AppCompatActivity {

    private static String TAG = EventActivity.class.getSimpleName();

    private Toolbar toolbar;

    private Data mData;
    private Categorie mCategorie;
    private Locatie mLocatie;

    private EventDB eventDB;
    private Constants.AnimType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // inside your activity (if you did not enable transitions in your theme (styles.xml) )
        // For AppCompat getWindow must be called before calling super.OnCreate
        // Must be called before setContentView
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventDB = new EventDB(this);

        mData = getIntent().getParcelableExtra("huidigDataObj");
        // Log.i(TAG, "dag van gekozen data object is : " + mData.getDay());

        mCategorie = getIntent().getParcelableExtra("huidigCategorieObj");
        // Log.i(TAG, "titel van gekozen categorie object is : " + mCategorie.getCategorieTitel());

        mLocatie = getIntent().getParcelableExtra("huidigLocatieObj");
        // Log.i(TAG, "naam van gekozen locatie object is : " + mLocatie.getLocatieNaam());

        // initialiseren variabelen
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_event);

        // instellen toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Tools.ConvertDay_To_DayStartingWithCap(mData));

        // verkrijg type uit intent vorige activity met bijhorende recycleradapter waar de enum wordt doorgegeven
        type = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);

        // kijk indien mCategorie object niet null is, indien men een opvraging doet vanuit CategorieActivity
        if (mCategorie != null) {
            toolbar.setSubtitle(mCategorie.getCategorieTitel());

            // lokale variabelen om methode getEventsByCategoryAndDate eenvoudiger te lezen
            int cat_id = Integer.valueOf(mCategorie.getCategorieID());
            int timestamp = Integer.valueOf(mData.getTimestamp());

            setUpDrawer();

            // events toewijzen per gekozen categorie en datum
            setUpRecyclerView(eventDB.getEventsByCategoryAndDate(cat_id, timestamp));

            Animation.setUpAnimation(type, this);
        }
        // kijk indien mLocatie object niet null is, indien men een opvraging doet vanuit LocatieActivity
        else if (mLocatie != null) {
            toolbar.setSubtitle(mLocatie.getLocatieNaam());

            // lokale variabelen om methode getEventsByLocationAndDate eenvoudiger te lezen
            int locatie_id = Integer.valueOf(mLocatie.getLocatieID());
            int timestamp = Integer.valueOf(mData.getTimestamp());

            setUpDrawer();

            // events toewijzen per gekozen locatie en datum
            setUpRecyclerView(eventDB.getEventsByLocationAndDate(locatie_id, timestamp));

            Animation.setUpAnimation(type, this);
        }
        // opvraging vanuit DataActivity als men op icoon Tijdstip klikt
        else {
            toolbar.setSubtitle("Kies een tijdstip");

            // lokale variabelen om methode getEventsByDate eenvoudiger te lezen
            int timestamp = Integer.valueOf(mData.getTimestamp());

            setUpDrawer();

            // events toewijzen per gekozen datum
            setUpRecyclerView(eventDB.getEventsByDate(timestamp));

            Animation.setUpAnimation(type, this);
        }
    }

    private void setUpRecyclerView(List<Event> events) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Tijdstip);
        // toekennen adapter aan huidige context en lijst categorie-objecten vanuit intent verkregen
        EventRecyclerAdapter adapter = new EventRecyclerAdapter(this, events, mData, mCategorie);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

        // Ook al gebruiken we dit niet, toon onze items als default
        // Check Documentation Google
        // dit kan perfect ook gecommented worden
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setUpDrawer() {
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment_activity_event);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drawer_fragment_activity_event, drawerLayout, toolbar);
    }

    // deze methode wordt aangeroepen als je klikt/duwt op de terugknop van het smartphone-toestel
    @Override
    public boolean onSupportNavigateUp () {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // start omgekeerde animatie-effect (in dit geval Explode-effect)
            // huidige Activity wordt vernietigd
            finishAfterTransition();
            return true;
        } else {
            // standaard waarde methode onSupportNavigateUp() voor toestellen onder Android 5.0 (Lollipop)
            return super.onSupportNavigateUp();
        }
    }

    // toon geen menu rechtsbovenaan toolbar in EventActivity
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_close_event:
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                /* start omgekeerde animatie-effect
                   huidige Activity wordt vernietigd
                   finish() wordt hierin automatisch en onmiddelijk aangeroepen, ook al is er is geen animatie voorzien als transitie */
                    finishAfterTransition();
                } else {
                    // gewone finish() aanroepen voor pré-Lollipop toestellen ( < Android 5.0)
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
