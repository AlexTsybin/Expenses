package com.alextsy.expenses.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertDate {

    public static String getDate() {
        Date currentDate = new Date();  // Current date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()); // Setting date format
        return sdf.format(currentDate);
    }

    public static String dateForDB() {
        Date currentDate = new Date();  // Current date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()); // Setting date format
        return sdf.format(currentDate);
    }

    public static String dateForList(String dbDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()); // Задаем формат даты
        Date date = null;
        try {
            date = sdf.parse(dbDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdfnew = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return sdfnew.format(date);
    }

}
