package example.com.yatzy;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout menuDrawer;
    private ActionBarDrawerToggle menuToggle;
    private String menuActivityTitle;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuActivityTitle = getTitle().toString();


        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupDrawer() {
        menuToggle = new ActionBarDrawerToggle(this, menuDrawer, R.string.side_menu_drawer_open, R.string.side_menu_drawer_close) {
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Make you pick...");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(menuActivityTitle);
                invalidateOptionsMenu();

            }
        };
        menuToggle.setDrawerIndicatorEnabled(true);
        menuDrawer.addDrawerListener(menuToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        menuToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        menuToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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

        // Activate the navigation drawer toggle
        if (menuToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(item.isChecked())
            item.setChecked(false);
       else
            item.setChecked(true);
        menuDrawer.closeDrawers();

        switch (item.getItemId()) {
            case R.id.nav_newGame:
                Toast.makeText(getApplicationContext(), "New Game selected", Toast.LENGTH_SHORT).show();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new StartGameFragment())
                        .commit();
                return true;
            case R.id.nav_Highscores:
                Toast.makeText(getApplicationContext(),"Highscores selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_PlayerStats:
                Toast.makeText(getApplicationContext(), "Player Stats selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_GameStats:
                Toast.makeText(getApplicationContext(), "Game Stats selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Syntax Error", Toast.LENGTH_SHORT).show();
                return true;
        }
    }
}
