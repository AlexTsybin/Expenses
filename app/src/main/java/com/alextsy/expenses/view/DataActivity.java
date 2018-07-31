package com.alextsy.expenses.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alextsy.expenses.App;
import com.alextsy.expenses.ExpensesRecyclerAdapter;
import com.alextsy.expenses.R;
import com.alextsy.expenses.model.AppDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_rows);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        db = App.getInstance().getDatabaseInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpensesRecyclerAdapter recyclerAdapter = new ExpensesRecyclerAdapter(this, db.expenseDao().getAllExpenses());
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
                finish();
//                startActivity(new Intent(DataActivity.this, MainActivity.class));
                break;
            }
            case R.id.action_delete: {
                int d = db.expenseDao().rowsDeleted();
                Toast.makeText(this, "Удалено " + d + " строк", Toast.LENGTH_LONG).show();
                startActivity(new Intent(DataActivity.this, MainActivity.class));
                break;
            }
        }
        return false;
    }

}
