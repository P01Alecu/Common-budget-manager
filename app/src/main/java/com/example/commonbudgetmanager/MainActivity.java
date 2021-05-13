package com.example.commonbudgetmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserOperations {

    public static final String PREFERENCES_KEY = "preferences key";
    public static final String PREFERENCES_ID_KEY = "preferences id key";

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            //incarca by default fragmentul cheltuieli
            FragCheltuieli fragCheltuieli = new FragCheltuieli();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragment_container, fragCheltuieli, null).commit();
        }
/*
        findViewById(R.id.button).setOnClickListener(view ->
                //makeSharedPreferences()
                insertUsers()
                );
*/
        //////test
        findViewById(R.id.text_buget).setOnClickListener(view ->
                    load_activity(new Intent(MainActivity.this, adaugaTranzactie.class))
                );


        ////
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.main:
                Toast.makeText(this, "main main main", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.log:
                Toast.makeText(this, "second second second", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void load_activity(Intent i){
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

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


    private void makeSharedPreferences(){
        //SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREFERENCES_ID_KEY, 10);
        editor.apply();

        int id = preferences.getInt(PREFERENCES_ID_KEY, 0);
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
    }

    @Override
    public void insertUsers(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void findUser(User user) {

    }
}