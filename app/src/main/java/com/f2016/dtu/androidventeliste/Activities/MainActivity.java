package com.f2016.dtu.androidventeliste.Activities;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Toast;
import com.f2016.dtu.androidventeliste.Fragments.HelpFragment;
import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Fragments.TriangerInfoFragment;
import com.f2016.dtu.androidventeliste.Fragments.ViewPagerFragment;
import com.f2016.dtu.androidventeliste.Utils.DataAccess;
import com.f2016.dtu.androidventeliste.Utils.DemoSession;
import com.f2016.dtu.androidventeliste.Utils.UserSession;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMainFragment();
        initInstances();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        customHandler.removeCallbacks(updateDataThread);
        customHandler = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        customHandler.postDelayed(updateDataThread, 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        customHandler.removeCallbacks(updateDataThread);
    }

    private void initMainFragment() {
        FragmentTransaction main_fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment main_fragment = new ViewPagerFragment();
        main_fragmentTransaction
                .add(R.id.main_fragment, main_fragment)
                .commit();
    }

    private Runnable updateDataThread = new Runnable() {

        public void run() {
            if (UserSession.getDemoSession() == null) {
                DataAccess data = new DataAccess();
                data.checkUserActive(UserSession.getPatientCode());
                if (UserSession.getUserAuth()) {
                    data.updateData();
                    customHandler.postDelayed(this, 10000);
                } else {
                    customHandler.removeCallbacks(this);
                    finish();
                }
            }
        }
    };

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        NavigationView navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                String stopDemoText = "Stopper demo";
                Fragment main_fragment = null;
                FragmentTransaction main_fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (id) {
                    case R.id.navigation_item_1:
                        main_fragment = new ViewPagerFragment();
                        break;
                    case R.id.navigation_item_2:
                        main_fragment = new TriangerInfoFragment();
                        break;
                    case R.id.navigation_item_3:
                        String startDemoText = "Starter demo version på 1 min";
                        if (UserSession.getDemoSession() != null) {
                            Toast.makeText(MainActivity.this,
                                    stopDemoText, Toast.LENGTH_LONG).show();
                            UserSession.getDemoSession().stopDemo();
                            UserSession.setDemoSession(null);
                        }
                        Toast.makeText(MainActivity.this,
                                startDemoText, Toast.LENGTH_LONG).show();
                        UserSession.setDemoSession(new DemoSession(6, MainActivity.this));
                        break;
                    case R.id.navigation_item_4:
                        String startDemoText1 = "Starter demo version på 2 min";
                        if (UserSession.getDemoSession() != null) {
                            Toast.makeText(MainActivity.this,
                                    stopDemoText, Toast.LENGTH_LONG).show();
                            UserSession.getDemoSession().stopDemo();
                            UserSession.setDemoSession(null);
                        }
                        Toast.makeText(MainActivity.this,
                                startDemoText1, Toast.LENGTH_LONG).show();
                        UserSession.setDemoSession(new DemoSession(12, MainActivity.this));
                        break;
                    case R.id.navigation_item_5:
                        if (UserSession.getDemoSession() != null) {
                            Toast.makeText(MainActivity.this,
                                    stopDemoText, Toast.LENGTH_LONG).show();
                            UserSession.getDemoSession().stopDemo();
                            UserSession.setDemoSession(null);
                            if (customHandler != null) {
                                customHandler.removeCallbacks(updateDataThread);
                                customHandler.postDelayed(updateDataThread, 100);
                            }
                        }
                        break;
                    case R.id.navigation_item_6:
                        main_fragment = new HelpFragment();
                        break;
                }
                if (main_fragment != null) {
                    main_fragmentTransaction
                            .replace(R.id.main_fragment, main_fragment)
                            .commit();
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}