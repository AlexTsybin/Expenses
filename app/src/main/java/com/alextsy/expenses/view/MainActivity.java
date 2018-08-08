package com.alextsy.expenses.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alextsy.expenses.R;
import com.alextsy.expenses.presenter.MainPresenter;
import com.alextsy.expenses.presenter.PresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewMvp.ViewMain {

    private static final String TAG = "MainActivity";

    private PresenterMvp.PresenterMain mPresenterMain;

    @BindView(R.id.numberField) TextView numberField;
    @BindView(R.id.day_spent_amount) TextView daySpentAmount;
    @BindView(R.id.month_spent_amount) TextView monthSpentAmount;
    @BindView(R.id.gridview) GridView mCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenterMain = new MainPresenter(this);
        mPresenterMain.onUpdateDaySpent();
        mPresenterMain.onUpdateMonthSpent();

        String[] categories = getResources().getStringArray(R.array.categories);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.grid_item_row, categories);
        mCategoryList.setAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenterMain.onCategoryButtonWasClicked(getApplicationContext(), view, parent.getItemAtPosition(position).toString());
            }
        };
        mCategoryList.setOnItemClickListener(itemListener);
    }

    // Supporting methods
    public boolean priceIsEmpty() {
        return numberField.getText().toString().isEmpty();
    }
    public int fieldLength() {
        return numberField.getText().toString().length();
    }
    public boolean startsWithZero() {
        return numberField.getText().toString().startsWith("0");
    }

    // Add number
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Number button was clicked
    public void onAddNumber(View view){
        Button numberBtn = (Button) view;
        String number = (String) numberBtn.getText();
        mPresenterMain.onAddButtonWasClicked(this, number);
    }

    // Set number to price if zero
    public void setNumber(String number) {
        numberField.setText(number);
    }

    // Append number to price
    public void appendNumber(String number) {
        numberField.append(number);
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    // Add zero
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Zero number was clicked
    public void onAddZero(View view) {
        mPresenterMain.onZeroButtonWasClicked(this);
    }

    public void addZero(String zero) {
        numberField.append(zero);
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    // Delete number
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Delete (c) number was clicked
    public void onDeleteNumber(View view) {
        mPresenterMain.onDeleteButtonWasClicked(this);
    }

    // Remove number from price
    public void deleteNumber() {
        numberField.setText(numberField.getText().subSequence(0, fieldLength() - 1));
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    // Category choosing
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    // Category choosing
//    public void onCategory(View view) {
//        Button categoryBtn = (Button) view;
//        mPresenterMain.onCategoryButtonWasClicked(this, categoryBtn);
//    }

    @Override
    public void clearPrice() {
        numberField.setText("");
    }

    @Override
    public void showDaySpent(String daySpent) {
        daySpentAmount.setText(daySpent);
    }

    @Override
    public void showMonthSpent(String monthSpent) {
        monthSpentAmount.setText(monthSpent);
    }
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    @Override
    public String getAmount() {
        if (priceIsEmpty()) {
            return String.valueOf(0);
        }
        return numberField.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenterMain.onDestroy();
        Log.d(TAG, "MainActivity.onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_data:
                Intent intent_data = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent_data);
                return true;
            case R.id.action_chart:
                Intent intent_chart = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(intent_chart);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Disabled function
//    public void onAddDot(ViewMain view) {
//        Button button = (Button) view;
//        if (priceIsEmpty()) {
//            numberField.append("0" + button.getText());
//        } else if (fieldLength() == getResources().getInteger(R.integer.price_field_max_length - 1)) {
//        } else {
//            if (!numberField.getText().toString().contains(".")) {
//                numberField.append(button.getText());
//            }
//        }
//    }
}
