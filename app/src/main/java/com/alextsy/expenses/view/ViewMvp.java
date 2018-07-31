package com.alextsy.expenses.view;

public interface ViewMvp {

    interface View {
        void showDaySpent(String message);
        void showMonthSpent(String monthSpent);
        String getAmount();
    }

}
