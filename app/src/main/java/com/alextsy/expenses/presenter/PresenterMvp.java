package com.alextsy.expenses.presenter;

import android.content.Context;
import android.view.View;

public interface PresenterMvp {

    interface PresenterMain {
        void onDestroy();

        void onCategoryButtonWasClicked(Context context, View view, String categoryName);
        void onDeleteButtonWasClicked(Context context);
        void onAddButtonWasClicked(Context context, String number);
        void onZeroButtonWasClicked(Context context);
        void onUpdateDaySpent();
        void onUpdateMonthSpent();
    }

    interface PresenterData {
        void onCreate(Context context);
        void onResume(Context context);
        void onMenuDeleteClick(Context context);
        void onMenuAddClick(Context context);
    }

}
