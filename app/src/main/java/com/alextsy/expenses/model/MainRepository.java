package com.alextsy.expenses.model;

import android.util.Log;

import com.alextsy.expenses.App;
import com.alextsy.expenses.Utils.ConvertDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainRepository implements RepositoryMvp.Repository {

    private static final String TAG = "MainRepository";

    AppDatabase db = App.getInstance().getDatabaseInstance();

    @Override
    public String getDaySpent() {
        Log.d(TAG, "MainRepository.getDaySpent()");
        // Здесь обращаемся к БД или сети.
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String s = ConvertDate.getDate();
        return db.expenseDao().getDaySpent(s);
    }

    @Override
    public String getMonthSpent() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return db.expenseDao().getMonthSpent(sdf.format(getFirstDateOfMonth(new Date())), sdf.format(getLastDateOfMonth(new Date())));
    }

    private Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    private Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

}
