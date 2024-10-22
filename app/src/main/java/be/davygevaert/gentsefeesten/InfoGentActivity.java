package be.davygevaert.gentsefeesten;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import be.davygevaert.gentsefeesten.constanten.Constants;
import be.davygevaert.gentsefeesten.fragment.NavigationDrawerFragment;
import be.davygevaert.gentsefeesten.tools.Animation;


public class InfoGentActivity extends AppCompatActivity {

    private static final String TAG = InfoGentActivity.class.getSimpleName();

    private Toolbar toolbar;
    private Constants.AnimType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_gent);

        // initialiseren variabelen
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_info);

        // instellen toolbar
        setSupportActionBar(toolbar);

        // instellen titel toolbar
        getSupportActionBar().setTitle("Info Gent");

        // verkrijg type uit intent vorige activity met bijhorende recycleradapter waar de enum wordt doorgegeven
        type = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);

        setUpDrawer();

        Animation.setUpAnimation(type, this);
    }

    private void setUpDrawer() {
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment_activity_info);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drawer_fragment_activity_info, drawerLayout, toolbar);
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
        getMenuInflater().inflate(R.menu.menu_info_gent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_close_info_gent:
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
