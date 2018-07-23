package com.alextsy.expenses.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense")
    List<Expense> getAllExpenses();

    @Query("DELETE FROM expense")
    int rowsDeleted();

}
