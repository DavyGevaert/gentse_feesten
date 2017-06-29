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

import be.davygevaert.gentsefeesten.adapter.EventDataRecyclerAdapter;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.databank.EventDB;
import be.davygevaert.gentsefeesten.fragment.NavigationDrawerFragment;
import be.davygevaert.gentsefeesten.tools.Animation;


public class DataActivity extends AppCompatActivity {

    private static String TAG = DataActivity.class.getSimpleName();

    private Toolbar toolbar;
    private EventDB eventDB;
    // private DataDB dataDB;
    private Constants.AnimType type;
    private String reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_data);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Gentse Feesten 2017");
        toolbar.setSubtitle("Maak een keuze per dag");

        // verkrijg type uit intent vorige activity met bijhorende recycleradapter waar de enum wordt doorgegeven
        type = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);

        // verkrijg String waarde reset als je klikt op home in navigation drawer
        reset = getIntent().getStringExtra("navigatie_naar_home_vanuit_navigation_drawer");

        eventDB = new EventDB(this);
        //dataDB = new DataDB(this);

        setUpDrawer();

        setUpRecyclerView();

        // enkel animatie voorzien als men op home klikt vanuit de navigation drawer
        if (reset != null && reset.equalsIgnoreCase("reset")) {
            Animation.setUpAnimation(type, this);
        }
    }

    private void setUpRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Data);
        // toekennen adapter aan huidige context en lijst data-objecten vanuit database verkrijgen
        EventDataRecyclerAdapter adapter = new EventDataRecyclerAdapter(this, eventDB.getAllDataShortNotation());
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
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment_activity_data);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drawer_fragment_activity_data, drawerLayout, toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_close_data:
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
