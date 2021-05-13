package com.example.commonbudgetmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");

        //meniu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //

        if(savedInstanceState == null) {
            //incarca by default fragmentul cheltuieli
            FragCheltuieli fragCheltuieli = new FragCheltuieli();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragment_container, fragCheltuieli, null).commit();
        }

        findViewById(R.id.text_buget).setOnClickListener(view ->
//                    load_activity(new Intent(MainActivity.this, adaugaTranzactie.class))
                  load_activity(new Intent(MainActivity.this, adaugaUtilizator.class))
        );
        findViewById(R.id.butonCheltuieli).setOnClickListener(view ->
                load_frag(new FragCheltuieli())
                );
        findViewById(R.id.butonVenituri).setOnClickListener(view ->
                load_frag(new FragVenituri())
                );
    }

    private void load_frag(Fragment fragment){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void load_activity(Intent i){
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);
        return true;
    }

/*
    private void insertUsers(){
        User user1 = new User(
                1,
                "Alecu",
                "Gabriel",
                22
        );
        User user2 = new User(
                2,
                "Nume",
                "Prenume",
                25
        );
        User[] userList = new User[]{user1, user2};

        new insertUserOperation(this).execute(userList);
    }
*/
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public void insertUsers(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void findUser(User user) {

    }
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.log:
                startActivity(new Intent(MainActivity.this, adaugaUtilizator.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.tranzactie:
                startActivity(new Intent(MainActivity.this, adaugaTranzactie.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}