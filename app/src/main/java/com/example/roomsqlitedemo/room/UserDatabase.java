package com.example.roomsqlitedemo.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * appDataBase
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {


    public abstract UserDao userDao();
}
