package com.alextsy.expenses;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.alextsy.expenses.model.AppDatabase;

public class App extends Application {

    public static App instance;
    private AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabaseInstance() {
        return db;
    }

}
