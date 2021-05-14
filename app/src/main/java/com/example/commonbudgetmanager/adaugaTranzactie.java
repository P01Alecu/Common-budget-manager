package com.example.commonbudgetmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class adaugaTranzactie extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adauga_tranzactie);

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

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        final EditText sumaInput = findViewById(R.id.editTextSuma);
        final EditText descriereInput = findViewById(R.id.editTextDescriere);

        Button saveButton = findViewById(R.id.butonAdaugaTranzactie);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveNewTransaction(name, descriereInput.getText().toString(), sumaInput.getText().toString());
            }
        });
        
    }

    private void saveNewTransaction(String name, String descriere, String suma){
        AppDatabase db = AppDatabase.getDbInstance((this.getApplicationContext()));

        User user = new User(name, descriere, Double.parseDouble(suma));
        //user.name = name;
        //user.suma = Double.parseDouble(suma);
        db.userDao().insertTransaction(user);

    }

    //menu relea
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                startActivity(new Intent(adaugaTranzactie.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.log:
                startActivity(new Intent(adaugaTranzactie.this, adaugaUtilizator.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.tranzactie:
                startActivity(new Intent(adaugaTranzactie.this, adaugaTranzactie.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
