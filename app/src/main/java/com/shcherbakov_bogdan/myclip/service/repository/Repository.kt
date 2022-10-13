package com.shcherbakov_bogdan.myclip.service.repository

import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import io.reactivex.Observable
import javax.inject.Inject

typealias TransactionListener = (transition: List<Transactions>) -> Unit

class Repository
@Inject constructor(
    private val remote: Remote,
    private val database: com.shcherbakov_bogdan.myclip.data.Database,
) {

    private val listeners = mutableSetOf<TransactionListener>()
    private var transactions = mutableListOf<Transactions>()

    fun getTransactionsList(): List<Transactions> = database.transactionDao().getAllTransactions()
    fun addListener(listener: TransactionListener) {
        listeners.add(listener)
        listener.invoke(transactions)
    }

    fun addTransactions(transaction: Transactions) {
        database.transactionDao().insert(transaction)
        notifyChanges()
    }

    fun removeListener(listener: TransactionListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(transactions) }
    }

    fun rates(periodicity: Int): Observable<List<CurrencyRates>> {
        return remote.rates(periodicity)
    }
}
