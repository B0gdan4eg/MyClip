package com.shcherbakov_bogdan.myclip.ui.fragments.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.data.sms.TransactionFromSms
import com.shcherbakov_bogdan.myclip.utils.Const.Companion.DATE_FORMAT
import com.shcherbakov_bogdan.myclip.utils.getDate

class InboxListAdapter(private val inboxViewModel: InboxViewModel) : RecyclerView
.Adapter<InboxListAdapter.TransactionFromSmsViewHolder>() {

    private var transactionFromSms: List<TransactionFromSms> = emptyList()

    fun refreshList(transactionFromSms: List<TransactionFromSms>) {
        this.transactionFromSms = transactionFromSms
        notifyDataSetChanged()
    }


    class TransactionFromSmsViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        val accountNameTextView: TextView = itemView.findViewById(R.id.account)
        val amountTextView: TextView = itemView.findViewById(R.id.amount)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionFromSmsViewHolder {
        return TransactionFromSmsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction_from_sms, parent, false) as ConstraintLayout
        )
    }

    override fun onBindViewHolder(holder: TransactionFromSmsViewHolder, position: Int) {
        holder.accountNameTextView.text = "Priorbank"
        holder.amountTextView.text = transactionFromSms[position].amount.toString()
        holder.dateTextView.text = getDate(transactionFromSms[position].date.toLong(), DATE_FORMAT)
    }

    override fun getItemCount(): Int {
        return transactionFromSms.size
    }

}