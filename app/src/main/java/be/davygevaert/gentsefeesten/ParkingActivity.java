package be.davygevaert.gentsefeesten;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import be.davygevaert.gentsefeesten.adapter.ParkingRecyclerAdapter;
import be.davygevaert.gentsefeesten.asynctask.VerkrijgParkingsTask;
import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.fragment.NavigationDrawerFragment;
import be.davygevaert.gentsefeesten.interfaces.AsyncResponse;
import be.davygevaert.gentsefeesten.model.Parking;
import be.davygevaert.gentsefeesten.tools.Animation;


public class ParkingActivity extends AppCompatActivity {

    private static String TAG = ParkingActivity.class.getSimpleName();

    private Toolbar toolbar;
    private Context context;

    private ArrayList<Parking> parkingsLijst = null;

    // globaal plaatsen
    private RecyclerView recyclerView;
    private ParkingRecyclerAdapter adapter;
    private Constants.AnimType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        context = this;

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_data);
        setSupportActionBar(toolbar);

        // instellen titel
        getSupportActionBar().setTitle("Overzicht parkings");

        // verkrijg type uit intent vorige activity met bijhorende recycleradapter waar de enum wordt doorgegeven
        type = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);

        // verkrijg ArrayList parkingsLijst uit Intent van NavigationDrawerAdapter
        parkingsLijst = getIntent().getParcelableArrayListExtra("parkings");

        for (Parking p : parkingsLijst) {
            // Log.i(TAG, "inhoud parking : " + p.getName() + " " + p.getStatus().getAvailableCapacity() + "/" + (int) p.getStatus().getTotalCapacity());
        }

        setUpDrawer();

        setUpRecyclerView(parkingsLijst);

        if (parkingsLijst != null) {
        } else {
            Toast.makeText(this, "Momenteel geen gegevens beschikbaar\nProbeer het later eens opnieuw.", Toast.LENGTH_LONG).show();
        }

        Animation.setUpAnimation(type, this);
    }

    private void setUpRecyclerView(ArrayList<Parking> parkingArrayList) {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Parking);
        // toekennen adapter aan huidige context en lijst data-objecten vanuit database verkrijgen
        adapter = new ParkingRecyclerAdapter(this, parkingArrayList);
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
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment_activity_parking);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drawer_fragment_activity_parking, drawerLayout, toolbar);
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
        getMenuInflater().inflate(R.menu.menu_parking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_refresh_parking:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new VerkrijgParkingsTask(new AsyncResponse() {
                            @Override
                            public void processFinish(ArrayList<Parking> parkingArrayList) {
                                parkingsLijst = parkingArrayList;

                                for (Parking p : parkingsLijst) {
                                    Log.i(TAG, "inhoud parking : " + p.getName() + " " + (p.getStatus().getTotalCapacity() - p.getStatus().getAvailableCapacity()) + "/" + (int) p.getStatus().getTotalCapacity());
                                }

                                adapter = new ParkingRecyclerAdapter(context, parkingArrayList);
                                recyclerView.setAdapter(adapter);

                                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(context);
                                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

                                // Ook al gebruiken we dit niet, toon onze items als default
                                // Check Documentation Google
                                // dit kan perfect ook gecommented worden
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                            }
                        }).execute(Constants.URL_BEZETTING_PARKINGS_REALTIME);
                    }
                }, 3000);


                break;

            case R.id.action_close_parking:
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
