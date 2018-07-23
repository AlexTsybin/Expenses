package com.alextsy.expenses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alextsy.expenses.model.Expense;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpensesRecyclerAdapter extends RecyclerView.Adapter<ExpensesRecyclerAdapter.ViewHolder> {

    private List<Expense> expenses;
    private Context context;
    private Locale locale;

    public ExpensesRecyclerAdapter(Context context, List<Expense> expenses) {
        this.context = context;
        this.expenses = expenses;
    }

    @Override
    public ExpensesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.db_row, parent, false);
        return new ViewHolder(view);
    }

    public String getMoney(int position) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(0);
        return format.format(Long.parseLong(expenses.get(position).getAmount()));
    }

    @Override
    public void onBindViewHolder(ExpensesRecyclerAdapter.ViewHolder viewHolder, int position) {
        viewHolder.current_date.setText(expenses.get(position).getDate());
        viewHolder.current_amount.setText(getMoney(position));
        viewHolder.current_descr.setText(expenses.get(position).getCostItem());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.current_date)
        TextView current_date;
        @BindView(R.id.current_amount)
        TextView current_amount;
        @BindView(R.id.current_descr)
        TextView current_descr;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
