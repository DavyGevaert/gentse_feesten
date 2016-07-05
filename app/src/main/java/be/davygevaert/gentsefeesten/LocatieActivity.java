package be.davygevaert.gentsefeesten;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import be.davygevaert.gentsefeesten.adapter.LocatieRecyclerAdapter;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.databank.LocatieDB;
import be.davygevaert.gentsefeesten.fragment.NavigationDrawerFragment;
import be.davygevaert.gentsefeesten.model.Data;
import be.davygevaert.gentsefeesten.tools.Animation;
import be.davygevaert.gentsefeesten.tools.Tools;


public class LocatieActivity extends AppCompatActivity {

    private static String TAG = LocatieActivity.class.getSimpleName();

    private Data mData;
    private Toolbar toolbar;
    private LocatieDB locatieDB;
    private Constants.AnimType type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // inside your activity (if you did not enable transitions in your theme (styles.xml) )
        // For AppCompat getWindow must be called before calling super.OnCreate
        // Must be called before setContentView
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatie);

        mData = getIntent().getParcelableExtra("huidigDataObj");
        Log.i(TAG, "dag van gekozen data object is : " + mData.getDay());

        // verkrijgen waarden uit Intent
        type = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);
        mData = getIntent().getParcelableExtra("huidigDataObj");

        // initialiseren variabelen
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_locatie);

        // instellen toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Tools.ConvertDay_To_DayStartingWithCap(mData));
        toolbar.setSubtitle("Kies een locatie");

        locatieDB = new LocatieDB(this);

        setUpDrawer();

        setUpRecyclerView();

        Animation.setUpAnimation(type, this);
    }

    private void setUpRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Locatie);
        // toekennen adapter aan huidige context en lijst locatie-objecten vanuit intent verkregen
        LocatieRecyclerAdapter adapter = new LocatieRecyclerAdapter(this, locatieDB.getLocaties(), mData);
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
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment_activity_locatie);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drawer_fragment_activity_locatie, drawerLayout, toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locatie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_close_location:
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
