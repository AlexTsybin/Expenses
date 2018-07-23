package com.alextsy.expenses.view;

public interface ViewMvp {

    interface View {
        void showText(String message);
        String getAmount();
    }

}
