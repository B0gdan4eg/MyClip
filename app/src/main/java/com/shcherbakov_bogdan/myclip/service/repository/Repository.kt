package com.shcherbakov_bogdan.myclip.service.repository

import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

typealias TransactionListener = (transition: List<Transactions>) -> Unit

class Repository
@Inject constructor(
    private val remote: Remote,
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao
) {
    fun getListOfTransactions() = transactionDao.getListOfTransactions()


}
