package com.example.commonbudgetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

/*
        findViewById(R.id.button).setOnClickListener(view ->
                //makeSharedPreferences()
                insertUsers()
                );
*/

        findViewById(R.id.butonCheltuieli).setOnClickListener(view ->
                load_frag(0)
                );
        findViewById(R.id.butonVenituri).setOnClickListener(view ->
                load_frag(1)
                );

    }

    private void load_frag(int index){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(index == 0) {
            FragCheltuieli fragCheltuieli = new FragCheltuieli();
            fragmentTransaction.replace(R.id.fragment_container, fragCheltuieli);

            fragmentTransaction.detach(fragCheltuieli);
        }
        else{
            FragVenituri fragVenituri = new FragVenituri();
            fragmentTransaction.replace(R.id.fragment_container, fragVenituri);

            fragmentTransaction.detach(fragVenituri);
        }
        fragmentTransaction.commit();
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