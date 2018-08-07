package com.alextsy.expenses.view;

import com.alextsy.expenses.ExpensesRecyclerAdapter;

public interface ViewMvp {

    interface ViewMain {
        void showDaySpent(String message);
        void showMonthSpent(String monthSpent);
        String getAmount();

        void deleteNumber();
        void setNumber(String number);
        void appendNumber(String number);
        void addZero(String zero);
        void clearPrice();
        boolean priceIsEmpty();
        int fieldLength();
        boolean startsWithZero();
    }

    interface ViewData {
        void resume(ExpensesRecyclerAdapter recyclerAdapter);
        void actionDeleteAll(int rows);
    }

}
