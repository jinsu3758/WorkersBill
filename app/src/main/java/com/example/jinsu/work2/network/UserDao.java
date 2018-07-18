/*
package com.example.jinsu.work2.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.jinsu.work2.Model.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertUser(User user);

    @Query("select login from user")
    String getUserId();

    @Query("select exists (select * from user)")
    boolean isUser();

    @Query("select * from user where login = :userId")
    LiveData<User> load(String userId);
}
*/
