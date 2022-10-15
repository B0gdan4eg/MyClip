package com.shcherbakov_bogdan.myclip.service.repository

import androidx.lifecycle.LiveData
import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import java.util.*
import javax.inject.Inject

typealias TransactionListener = (transition: List<Transactions>) -> Unit

class Repository
@Inject constructor(
    private val remote: Remote,
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao
) {
    private var transactions = mutableListOf<Transactions>()
    private var loaded = false
    var listeners = mutableSetOf<TransactionListener>()

    fun loadUsers() : List<Transactions>{
        transactions = transactionDao.getListOfTransactions()
        loaded = true
        notifyChanges()
        return transactions
    }

    fun deleteUser(transition: Transactions) {
            notifyChanges()
        listeners
        }

    fun addListener(listener: TransactionListener) {
        listeners.add(listener)
        if (loaded) {
            listener.invoke(transactions)
        }
    }

    fun removeListener(listener: TransactionListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        if (!loaded) return
        listeners.forEach { it.invoke(transactions) }
    }
}
