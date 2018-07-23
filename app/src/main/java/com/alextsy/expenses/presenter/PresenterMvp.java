package com.alextsy.expenses.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.alextsy.expenses.view.MainActivity;

public interface PresenterMvp {

    interface Presenter {
        void onDestroy();

        void onCategoryButtonWasClicked(Context context, Button categoryBtn);
    }

}
