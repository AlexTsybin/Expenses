package com.alextsy.expenses.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alextsy.expenses.ExpensesRecyclerAdapter;
import com.alextsy.expenses.R;
import com.alextsy.expenses.presenter.DataPresenter;
import com.alextsy.expenses.presenter.PresenterMvp;

import java.util.ArrayList;

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        PieView pieView = findViewById(R.id.pie_view);
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<>();
        pieView.setDate(pieHelperArrayList);
        pieView.selectedPie(2); //optional
//        pieView.setOnPieClickListener(listener); //optional
        pieView.showPercentLabel(false); //optional
    }
}
