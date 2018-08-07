package com.alextsy.expenses.presenter;

import android.content.Context;
import android.widget.Button;

public interface PresenterMvp {

    interface PresenterMain {
        void onDestroy();

        void onCategoryButtonWasClicked(Context context, Button categoryBtn);
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
        void onMenuAddclick(Context context);
    }

}
