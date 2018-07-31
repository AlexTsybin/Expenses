package com.alextsy.expenses.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.alextsy.expenses.App;
import com.alextsy.expenses.model.AppDatabase;
import com.alextsy.expenses.model.Expense;
import com.alextsy.expenses.model.MainRepository;
import com.alextsy.expenses.model.RepositoryMvp;
import com.alextsy.expenses.view.DataActivity;
import com.alextsy.expenses.view.ViewMvp;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainPresenter implements PresenterMvp.Presenter {

    private static final String TAG = "MainPresenter";

    // MVP components
    private RepositoryMvp.Repository mRepository;
    private ViewMvp.View mView;

    AppDatabase db = App.getInstance().getDatabaseInstance();
    private String spentDayAmount;
    private String spentMonthAmount;

    public Context context;

    public MainPresenter(ViewMvp.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
        Log.d(TAG, "MainPresenter.Constructor()");
    }

    public String getDate() {
        Date currentDate = new Date();  // Текущая дата
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); // Задаем формат даты
        return sdf.format(currentDate);
    }

    // View calls that button was clicked
    public void updateDaySpentAmount() {
        spentDayAmount = mRepository.getDaySpent();

        if (spentDayAmount != null) {
            mView.showDaySpent(getMoney(spentDayAmount));
        } else {
            mView.showDaySpent("No purchases");
        }
        Log.d(TAG, "MainPresenter.updateDaySpentAmount()");
    }

    public void updateMonthSpentAmount() {
        spentMonthAmount = mRepository.getMonthSpent();
        if (spentMonthAmount != null) {
            mView.showMonthSpent(getMoney(spentMonthAmount));
        } else {
            mView.showMonthSpent("No purchases");
        }
    }

    public String getMoney(String amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(0);
        return format.format(Long.parseLong(amount));
    }

    @Override
    public void onDestroy() {
        // Unsubscribe in this method
        Log.d(TAG, "MainPresenter.onDestroy()");
    }

    @Override
    public void onCategoryButtonWasClicked(Context context, Button categoryBtn) {
        if (mView.getAmount().equals(String.valueOf(0))) {
            Toast.makeText(context, "Enter the price!", Toast.LENGTH_SHORT).show();
            return;
        }

        Expense exp = new Expense(getDate(), mView.getAmount(), categoryBtn.getText().toString());
        db.expenseDao().insert(exp);

        updateDaySpentAmount();

        context.startActivity(new Intent(context, DataActivity.class));
    }

    @Override
    public void onUpdateDaySpent() {
        updateDaySpentAmount();
    }

    @Override
    public void onUpdateMonthSpent() {
        updateMonthSpentAmount();
    }

}
