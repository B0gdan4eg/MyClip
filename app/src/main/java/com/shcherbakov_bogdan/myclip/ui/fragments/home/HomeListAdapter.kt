package com.shcherbakov_bogdan.myclip.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.data.transactions.*

class HomeListAdapter(private val transactions: List<Transactions>)  : RecyclerView
.Adapter<HomeListAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout){
        val expensesTextView: TextView = itemView.findViewById(R.id.expenses)
        val incomeTextView: TextView = itemView.findViewById(R.id.income)
        val dateDayTextView: TextView = itemView.findViewById(R.id.date_day)
        val toWeeksTextView: TextView = itemView.findViewById(R.id.to_weeks)
        val monthYearTextView: TextView = itemView.findViewById(R.id.month_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false) as ConstraintLayout
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        if (getConditionOfTransaction(transactions[position])) {
            holder.incomeTextView.text = getAmountOfTransaction(transactions[position])
            holder.expensesTextView.text = "0.00"
        } else {
            holder.incomeTextView.text = "0.00"
            holder.expensesTextView.text = getAmountOfTransaction(transactions[position])
        }
        holder.dateDayTextView.text = getTransactionDay(transactions[position])
        holder.toWeeksTextView.text = ""
        holder.monthYearTextView.text = getTransactionMonthYear(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}
