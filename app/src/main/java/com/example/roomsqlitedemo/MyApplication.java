package com.example.roomsqlitedemo;

import android.app.Application;

import com.example.roomsqlitedemo.room.AppDatabase;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.init();
            }
        }).start();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
