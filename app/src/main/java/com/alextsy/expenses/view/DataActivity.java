package com.alextsy.expenses.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alextsy.expenses.ExpensesRecyclerAdapter;
import com.alextsy.expenses.R;
import com.alextsy.expenses.presenter.DataPresenter;
import com.alextsy.expenses.presenter.PresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity implements ViewMvp.ViewData {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private PresenterMvp.PresenterData mPresenterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_rows);
        ButterKnife.bind(this);
        mPresenterData = new DataPresenter(this);

        mPresenterData.onCreate(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenterData.onResume(this);
    }

    public void resume(ExpensesRecyclerAdapter recyclerAdapter) {
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                mPresenterData.onMenuAddclick(this);
                break;
            }
            case R.id.action_delete_all: {
                mPresenterData.onMenuDeleteClick(this);
                break;
            }
        }
        return false;
    }

    public void actionDeleteAll(int rows) {
        Toast.makeText(this, "Удалено " + rows + " строк", Toast.LENGTH_LONG).show();
    }

}
