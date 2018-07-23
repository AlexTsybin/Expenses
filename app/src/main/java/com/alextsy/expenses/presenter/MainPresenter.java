package com.alextsy.expenses.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alextsy.expenses.App;
import com.alextsy.expenses.model.AppDatabase;
import com.alextsy.expenses.model.Expense;
import com.alextsy.expenses.model.MainRepository;
import com.alextsy.expenses.model.RepositoryMvp;
import com.alextsy.expenses.view.MainActivity;
import com.alextsy.expenses.view.RowActivity;
import com.alextsy.expenses.view.ViewMvp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainPresenter implements PresenterMvp.Presenter {

    private static final String TAG = "MainPresenter";

    // MVP components
    private RepositoryMvp.Repository mRepository;
    private ViewMvp.View mView;

    AppDatabase db = App.getInstance().getDatabaseInstance();
    private String message;

    public Context context;

    public MainPresenter(ViewMvp.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
        Log.d(TAG, "Constructor");
    }

    public String getDate() {
        Date currentDate = new Date();  // Текущая дата
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); // Задаем формат даты
        return sdf.format(currentDate);
    }

    // View calls that button was clicked
    public void onButtonWasClicked(Context context) {
//        message = mRepository.loadMessage();
//        mView.showText(message);
//        Log.d(TAG, "onButtonWasClicked()");
    }

    @Override
    public void onDestroy() {
        // Unsubscribe in this method
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onCategoryButtonWasClicked(Context context, Button categoryBtn) {
        if (mView.getAmount().equals(String.valueOf(0))) {
            Toast.makeText(context, "Enter the price!", Toast.LENGTH_SHORT).show();
            return;
        }
        Expense exp = new Expense(getDate(), mView.getAmount(), categoryBtn.getText().toString());
        db.expenseDao().insert(exp);
        context.startActivity(new Intent(context, RowActivity.class));
    }

}
