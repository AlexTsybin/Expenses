package com.alextsy.expenses.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alextsy.expenses.App;
import com.alextsy.expenses.ExpensesRecyclerAdapter;
import com.alextsy.expenses.model.AppDatabase;
import com.alextsy.expenses.model.MainRepository;
import com.alextsy.expenses.model.RepositoryMvp;
import com.alextsy.expenses.view.MainActivity;
import com.alextsy.expenses.view.ViewMvp;

public class DataPresenter implements PresenterMvp.PresenterData {

    private static final String TAG = "DataPresenter";

    private ViewMvp.ViewData mViewData;
    private RepositoryMvp.Repository mRepository;

    private AppDatabase db;
    ExpensesRecyclerAdapter recyclerAdapter;

    public DataPresenter(ViewMvp.ViewData mViewData) {
        this.mViewData = mViewData;
        this.mRepository = new MainRepository();
        Log.d(TAG, "DataPresenter.Constructor()");
    }

    @Override
    public void onCreate(Context context) {
        db = App.getInstance().getDatabaseInstance();
    }

    @Override
    public void onResume(Context context) {
        recyclerAdapter = new ExpensesRecyclerAdapter(context, db.expenseDao().getAllExpenses());
        mViewData.resume(recyclerAdapter);
    }

    @Override
    public void onMenuAddClick(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onMenuDeleteClick(Context context) {
        int d = db.expenseDao().rowsDeleted();
        mViewData.actionDeleteAll(d);
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
