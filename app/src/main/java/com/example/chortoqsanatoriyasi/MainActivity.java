package com.example.chortoqsanatoriyasi;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chortoqsanatoriyasi.Prevalent.Prevalent;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView usernameTextView = headerView.findViewById(R.id.profile_name);
        CircleImageView profileImageView =headerView.findViewById(R.id.profile_image);
        Paper.init(this);

        if (Prevalent.CurrentOnlineUser==null)
        {
            Intent intent =new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else {
            usernameTextView.setText(Prevalent.CurrentOnlineUser.getIsm());
        }

        if (Prevalent.CurrentOnlineUser != null)
        {
            hideItem();
        }

        else {
            navigationView =(NavigationView)findViewById(R.id.nav_view);
            Menu nav_Menu =navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);

        }

    }

    private void hideItem()
    {
        navigationView =(NavigationView)findViewById(R.id.nav_view);
        Menu nav_Menu =navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_logout).setVisible(true);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_kirish) {

            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_bolimlar) {

            Intent intent=new Intent(MainActivity.this,BolimlarActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_haqida) {

        } else if (id == R.id.nav_logout) {

            Paper.book().destroy();
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            setVisible(false);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
