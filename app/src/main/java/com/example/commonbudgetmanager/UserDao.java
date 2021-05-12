package com.example.commonbudgetmanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    public List<User> getAll();

    @Query("SELECT firstName FROM user")
    public List<String> getFirstNames();


}
