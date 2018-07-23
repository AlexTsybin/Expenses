package com.alextsy.expenses.model;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

public class MainRepository implements RepositoryMvp.Repository {

    private static final String TAG = "MainRepository";

    @Override
    public String loadMessage() {
        Log.d(TAG, "loadMessage()");
        // Здесь обращаемся к БД или сети.
        return "Repository return";
    }

}
