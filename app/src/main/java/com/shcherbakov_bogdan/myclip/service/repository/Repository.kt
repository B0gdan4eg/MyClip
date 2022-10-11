package com.shcherbakov_bogdan.myclip.service.repository

import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import io.reactivex.Observable
import javax.inject.Inject

class Repository
@Inject constructor(
    private val remote: Remote,
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao
    ) {

    fun getTransactionsList() = transactionDao.getAllTransactions()

    fun rates(periodicity: Int): Observable<List<CurrencyRates>> {
        return remote.rates(periodicity)
    }
}
