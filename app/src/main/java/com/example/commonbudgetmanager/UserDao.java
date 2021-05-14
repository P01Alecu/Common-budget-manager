package com.example.commonbudgetmanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertTransaction(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    public List<User> getAllTransactions();

    @Query("SELECT * FROM user WHERE tranzactie = :type AND name = :name")
    public List<User> getTransaction(String name, String type);

    /*
    @Query("SELECT * FROM user WHERE name = :name AND tranzactie = :type")
    public List<User> getBuget(String name, String type);
    */
}
