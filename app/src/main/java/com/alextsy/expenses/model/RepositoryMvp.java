package com.alextsy.expenses.model;

public interface RepositoryMvp {

    interface Repository {
        String getDaySpent();
        String getMonthSpent();
    }

}
