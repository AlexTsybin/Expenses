package com.alextsy.expenses.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alextsy.expenses.App;
import com.alextsy.expenses.R;
import com.alextsy.expenses.Utils.ConvertDate;
import com.alextsy.expenses.model.AppDatabase;
import com.alextsy.expenses.model.Expense;
import com.alextsy.expenses.model.MainRepository;
import com.alextsy.expenses.model.RepositoryMvp;
import com.alextsy.expenses.view.DataActivity;
import com.alextsy.expenses.view.ViewMvp;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainPresenter implements PresenterMvp.PresenterMain {

    private static final String TAG = "MainPresenter";

    // MVP components
    private RepositoryMvp.Repository mRepository;
    private ViewMvp.ViewMain mViewMain;

    private AppDatabase db = App.getInstance().getDatabaseInstance();
    private String spentDayAmount;
    private String spentMonthAmount;

    public MainPresenter(ViewMvp.ViewMain mViewMain) {
        this.mViewMain = mViewMain;
        this.mRepository = new MainRepository();
        Log.d(TAG, "MainPresenter.Constructor()");
    }

    // Button handlers
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    @Override
    public void onAddButtonWasClicked(Context context, String number) {
        if (mViewMain.fieldLength() == 1 && mViewMain.startsWithZero()) {
            mViewMain.setNumber(number);
        } else {
            mViewMain.appendNumber(number);
        }
    }

    @Override
    public void onZeroButtonWasClicked(Context context) {
        if (mViewMain.fieldLength() == 1 && mViewMain.startsWithZero()) {
            return;
        } else {
            mViewMain.addZero(context.getString(R.string.zero_button));
        }
    }

    @Override
    public void onDeleteButtonWasClicked(Context context) {
        if (mViewMain.priceIsEmpty()) {
            return;
        }
        mViewMain.deleteNumber();
    }

    @Override
    public void onCategoryButtonWasClicked(Context context, View view, String categoryName) {
        if (mViewMain.getAmount().equals(String.valueOf(0))) {
            Toast.makeText(context, "Enter the price!", Toast.LENGTH_SHORT).show();
            return;
        }

        Expense exp = new Expense(ConvertDate.dateForDB(), mViewMain.getAmount(), firstUpperCase(categoryName.toLowerCase()));
        db.expenseDao().insert(exp);

        updateDaySpentAmount();
        updateMonthSpentAmount();

        Toast.makeText(context, "You spent " + mViewMain.getAmount(), Toast.LENGTH_LONG).show();

        mViewMain.clearPrice();

//        context.startActivity(new Intent(context, DataActivity.class));
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    // Supporting methods
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Money symbol
    public String getMoney(String amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(0);
        return format.format(Long.parseLong(amount));
    }

    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    // Updating purchasing
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    public void updateDaySpentAmount() {
        spentDayAmount = mRepository.getDaySpent();

        if (spentDayAmount != null) {
            mViewMain.showDaySpent(getMoney(spentDayAmount));
        } else {
            mViewMain.showDaySpent("No purchases");
        }
        Log.d(TAG, "MainPresenter.updateDaySpentAmount()");
    }

    public void updateMonthSpentAmount() {
        spentMonthAmount = mRepository.getMonthSpent();
        if (spentMonthAmount != null) {
            mViewMain.showMonthSpent(getMoney(spentMonthAmount));
        } else {
            mViewMain.showMonthSpent("No purchases");
        }
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    @Override
    public void onDestroy() {
        // Unsubscribe in this method
        Log.d(TAG, "MainPresenter.onDestroy()");
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
