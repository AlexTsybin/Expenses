package com.alextsy.expenses.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alextsy.expenses.R;
import com.alextsy.expenses.presenter.MainPresenter;
import com.alextsy.expenses.presenter.PresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewMvp.View, ChartFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    private PresenterMvp.Presenter mPresenter;

    @BindView(R.id.numberField) TextView numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);
        Log.d(TAG, "onCreate()");
    }

    public boolean priceIsEmpty() {
        return numberField.getText().toString().isEmpty();
    }
    public int fieldLength() {
        return numberField.getText().toString().length();
    }
    public boolean startsWithZero() {
        return numberField.getText().toString().startsWith("0");
    }

    public void onCategory(View view) {
        Button categoryBtn = (Button) view;
        mPresenter.onCategoryButtonWasClicked(this, categoryBtn);
    }

    // Add number to price
    public void onAddNumber(View view){
        Button numberBtn = (Button) view;
        if (fieldLength() == 1 && startsWithZero()) {
            numberField.setText(numberBtn.getText());
        } else {
            numberField.append(numberBtn.getText());
        }
    }

    public void onDeleteNumber(View view) {
        if (priceIsEmpty()) {
            return;
        }
        numberField.setText(numberField.getText().subSequence(0, fieldLength() - 1));
    }

    public void onAddZero(View view) {
        Button button = (Button) view;
        if (startsWithZero() && fieldLength() == 1) {
            return;
        } else {
            numberField.append(button.getText());
        }
    }

    @Override
    public void showText(String message) {
        numberField.setText(message);
        Log.d(TAG, "showMessage()");
    }

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
        mPresenter.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // Disabled function
//    public void onAddDot(View view) {
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
