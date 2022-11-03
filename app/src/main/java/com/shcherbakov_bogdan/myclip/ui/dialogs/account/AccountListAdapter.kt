package com.shcherbakov_bogdan.myclip.ui.dialogs.account

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.data.account.Account

class AccountListAdapter(private val context: Context, private val viewModel: AccountViewModel) :
    RecyclerView.Adapter<AccountListAdapter.AccountViewHolder>() {

    private var accounts: List<Account> = emptyList()

    fun refreshUsers(accounts: List<Account>) {
        this.accounts = accounts
        notifyDataSetChanged()
    }

    class AccountViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        val balance: TextView = itemView.findViewById(R.id.account_balance)
        val name: TextView = itemView.findViewById(R.id.account_name)
        val icon: ImageView = itemView.findViewById(R.id.account_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_account, parent, false) as ConstraintLayout
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val resourceId = context.resources.getIdentifier(
            accounts[position].icon, "drawable",
            context.packageName
        )
        holder.icon.setImageResource(resourceId)
        holder.icon.setImageDrawable(Drawable.createFromPath(accounts[position].icon))
        holder.balance.text = accounts[position].balance.toString()
        holder.name.text = accounts[position].name
    }

    override fun getItemCount(): Int {
        return accounts.size
    }
}