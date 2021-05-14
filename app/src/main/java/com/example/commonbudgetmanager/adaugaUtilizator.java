package com.example.commonbudgetmanager;

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

public class adaugaUtilizator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    EditText name;
    Button button;
    SharedPreferences sp;
    String nameStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adauga_utilizator);

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

        name = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.butonAdauga);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nameStr = name.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", nameStr);
                editor.commit();
                //Toast.makeText(this, "Informatii salvate", Toast.LENGTH_LONG).show();

                Intent i = new Intent(adaugaUtilizator.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    //menu related
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
                startActivity(new Intent(adaugaUtilizator.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.log:
                startActivity(new Intent(adaugaUtilizator.this, adaugaUtilizator.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.tranzactie:
                startActivity(new Intent(adaugaUtilizator.this, adaugaTranzactie.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
