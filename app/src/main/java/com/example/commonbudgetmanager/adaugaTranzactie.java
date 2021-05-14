package com.example.commonbudgetmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView imageView;

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
        //spinner
        Spinner dropdown = findViewById(R.id.spinner);
        String[] itemsForSpinner = new String[]{"Cheltuiala", "Venit"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsForSpinner);
        dropdown.setAdapter(adapter);

        final EditText sumaInput = findViewById(R.id.editTextSuma);
        sumaInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sumaInput.getText().clear();
            }
        });
        final EditText descriereInput = findViewById(R.id.editTextDescriere);
        descriereInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                descriereInput.getText().clear();
            }
        });

        imageView = (ImageView) findViewById(R.id.poza);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openGallery();
            }
        });


        Button saveButton = findViewById(R.id.butonAdaugaTranzactie);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Spinner tipTranzactie = findViewById(R.id.spinner);
                Integer selectedIndex = tipTranzactie.getSelectedItemPosition();
                saveNewTransaction(name, descriereInput.getText().toString(), sumaInput.getText().toString(), selectedIndex);
            }
        });
        
    }

    private void saveNewTransaction(String name, String descriere, String suma, Integer selectedIndex){
        AppDatabase db = AppDatabase.getDbInstance((this.getApplicationContext()));
        String tipTranzactie = selectedIndex == 0 ? "Cheltuiala" : "Venit";
        User user = new User(name, descriere, Double.parseDouble(suma), tipTranzactie);
        db.userDao().insertTransaction(user);
        finish();
    }

    //menu releated
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

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


}
