package com.shcherbakov_bogdan.myclip.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class Repository
@Inject constructor(
    private val remote: Remote,
    private val currencyDao: CurrencyDao,
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao
) : Repo {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    //Transactions
    override suspend fun addTransaction(transactions: Transactions) {
        withContext(ioDispatcher) {
            try {
                transactionDao.insert(transactions)
            } catch (e: Exception) {
                Log.e("TAG", "failed addCart : ${e.message}")
            }
        }
    }

    override suspend fun getTransactions(): List<Transactions>? =
        withContext(ioDispatcher) {
            var transactions: List<Transactions>? = null
            try {
                transactions = transactionDao.getListOfTransactions()
            } catch (e: Exception) {
                Log.e(TAG, "failed getTransaction: ${e.message} ")
            }
            return@withContext transactions
        }
//    suspend fun deleteTransaction(transactions: Transactions) {
//        withContext(ioDispatcher) {
//            try {
//                //transactionDao.
//            } catch (e: Exception) {
//                Log.e("TAG", "failed delete transaction : ${e.message}")
//            }
//        }
//    }
//
//    suspend fun deleteTransactions() {
//        withContext(ioDispatcher) {
//            try {
//                TransactionsDao.deleteTransactions()
//            } catch (e: Exception) {
//                Log.e("TAG", "failed delete transaction : ${e.message}")
//            }
//        }
//    }

    //Remote

    //Currency
    override suspend fun getCurrencies(): List<CurrencyRates>? =
        withContext(ioDispatcher) {
            var currencyRates: List<CurrencyRates>? = null
            try {
                currencyRates = currencyDao.getListOfCurrency()
            } catch (e: Exception) {
                Log.e(TAG, "failed getTransaction: ${e.message} ")
            }
            return@withContext currencyRates
        }

    suspend fun updateCurrencyFromRemote() {
        withContext(ioDispatcher) {
            val currencyRates : List<CurrencyRates> = listOf(
                remote.getExchangeRate("USD") ,
                remote.getExchangeRate("EUR") ,
                remote.getExchangeRate("RUB") ,
                remote.getExchangeRate("UAH")
            )
            currencyDao.deleteAllCurrencies()
            currencyDao.insert(currencyRates)
        }
    }

    //Account

    //Category


    companion object {
        private const val TAG = "TransactionRepository"
    }
}
