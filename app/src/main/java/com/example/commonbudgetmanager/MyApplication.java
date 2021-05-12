package com.example.commonbudgetmanager;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {

    private static MyApplication nInstance;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate(){
        super.onCreate();

        nInstance = this;
        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "database-name"
        ).build();
    }

    public static MyApplication getInstance(){
        return nInstance;
    }

    public static AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
