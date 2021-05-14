package com.example.commonbudgetmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String descriere;
    public Double suma;

    public User(String name, String descriere, Double suma){
        this.name = name;
        this.descriere = descriere;
        this.suma = suma;
    }
}
