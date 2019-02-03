package com.alextsy.expenses.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense ORDER BY date DESC")
    List<Expense> getAllExpenses();

    @Query("SELECT SUM(amount) FROM expense WHERE date = :currentDate")
    String getDaySpent(String currentDate);

    @Query("SELECT SUM(amount) FROM expense WHERE date BETWEEN :firstDay AND :lastDay")
    String getMonthSpent(String firstDay, String lastDay);

    @Query("DELETE FROM expense")
    int rowsDeleted();

}
