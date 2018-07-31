package com.alextsy.expenses.model;

import android.os.Build;
import android.util.Log;

import com.alextsy.expenses.App;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainRepository implements RepositoryMvp.Repository {

    private static final String TAG = "MainRepository";

    AppDatabase db = App.getInstance().getDatabaseInstance();

    public String getDate() {
        Date currentDate = new Date();  // Текущая дата
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); // Задаем формат даты
        return sdf.format(currentDate);
    }

    @Override
    public String getDaySpent() {
        Log.d(TAG, "MainRepository.getDaySpent()");
        // Здесь обращаемся к БД или сети.
        return db.expenseDao().getDaySpent(getDate());
    }

    @Override
    public String getMonthSpent() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return db.expenseDao().getMonthSpent(sdf.format(getFirstDateOfMonth(new Date())), sdf.format(getLastDateOfMonth(new Date())));
    }

    public Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

}
