package com.example.roomsqlitedemo.room;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.roomsqlitedemo.MyApplication;

public class AppDatabase extends RoomDatabase {

    private static AppDatabase sAppDatabase;
    private UserDatabase userDatabase;

    public AppDatabase() {
        userDatabase = Room.databaseBuilder(MyApplication.getInstance(),UserDatabase.class,"user").build();
    }

    /**
     * 获取AppDatabase实例
     *
     * @return AppDatabase
     */
    public static AppDatabase getInstance() {
        if (sAppDatabase == null) {
            throw new IllegalStateException("NoteRepository must be initialized");
        }
        return sAppDatabase;
    }

    public static void init() {
        if (sAppDatabase == null) {
            sAppDatabase = new AppDatabase();
        }
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public UserDao userDao() {
        return userDatabase.userDao();
    }
}
