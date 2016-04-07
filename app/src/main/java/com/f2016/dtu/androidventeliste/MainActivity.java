package com.f2016.dtu.androidventeliste;

import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainFragment();
        //initTopAndButtomFragments();
        initInstances();
    }

    private void initMainFragment() {
        FragmentTransaction main_fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //top_fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        Fragment main_fragment = new ViewPagerFragment();
        main_fragmentTransaction
                .add(R.id.main_fragment, main_fragment)
                .commit();
    }

    private void initTopAndButtomFragments() {
        FragmentTransaction top_fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //top_fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        Fragment top_fragment = new TopQueueFragment();
        top_fragmentTransaction
                .add(R.id.top_fragment, top_fragment)
                .commit();

        FragmentTransaction bot_fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //bot_fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        Fragment buttom_fragment = new BotQueueFragment();
        bot_fragmentTransaction
                .add(R.id.buttom_fragment, buttom_fragment)
                .commit();
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
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
                        main_fragment = new DrawQueueFragment();
                        break;
                }
                if(main_fragment != null) {
                    main_fragmentTransaction
                            .replace(R.id.main_fragment, main_fragment)
                            .commit();
                    drawerLayout.closeDrawers();
                }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

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
}