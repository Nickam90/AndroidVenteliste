package com.f2016.dtu.androidventeliste;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
import com.flipboard.bottomsheet.commons.MenuSheetView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initButtomInfo();
        initInstances();
    }

    private void initButtomInfo() {
        BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheet.showWithSheetView(LayoutInflater.from(this).inflate(R.layout.fragment_infobox, bottomSheet, false));

        //FragmentTransaction transaction = new FragmentTransaction().;
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
                switch (id) {
                    case R.id.navigation_item_1:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        break;
                    case R.id.navigation_item_2:
                        startActivity(new Intent(MainActivity.this,TriangerActivity.class));
                        break;
                    case R.id.navigation_item_3:
                        startActivity(new Intent(MainActivity.this,GoogleActivity.class));
                        break;
                    case R.id.navigation_item_4:
                        startActivity(new Intent(MainActivity.this,TriangerActivity.class));
                        break;
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