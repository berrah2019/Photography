package com.example.Unsplash.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.Unsplash.Fragments.Collections_fragment;
import com.example.Unsplash.Fragments.Favorite_fragment;


import com.example.Unsplash.Fragments.Photo_Fragment;

import com.example.Unsplash.R;
import com.example.Unsplash.Utils.Functions;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener( this);

        Photo_Fragment homeFragment = new Photo_Fragment();

        Functions.change_mainFragment(MainActivity.this, homeFragment);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_photos) {
            Photo_Fragment photo_fragment = new Photo_Fragment();
            Functions.change_mainFragment(MainActivity.this, photo_fragment);
        } else if (id == R.id.nav_collections) {
            Collections_fragment collections_fragment = new Collections_fragment();
            Functions.change_mainFragment(MainActivity.this, collections_fragment);

        } else if (id == R.id.nav_favorite) {
            Favorite_fragment favorite_fragment = new Favorite_fragment();
            Functions.change_mainFragment(MainActivity.this, favorite_fragment);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
