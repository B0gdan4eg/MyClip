package com.shcherbakov_bogdan.myclip.service.repository

import android.util.Log
import com.shcherbakov_bogdan.myclip.data.account.Account
import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.sms.SmsDao
import com.shcherbakov_bogdan.myclip.data.sms.TransactionFromSms
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository
@Inject constructor(
    private val remote: Remote,
    private val currencyDao: CurrencyDao,
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao,
    private val categoryDao: CategoryDao,
    private val smsDao: SmsDao,
) : Repo {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    //Transactions
    override suspend fun addTransaction(transactions: Transactions) {
        withContext(ioDispatcher) {
            try {
                transactionDao.insert(transactions)
            } catch (e: Exception) {
                Log.e("TAG", "failed addTransaction : ${e.message}")
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
                Log.e(TAG, "failed getCurrencies: ${e.message} ")
            }
            return@withContext currencyRates
        }

    private suspend fun insertCurrencies(list: List<CurrencyRates>?) {
        if (list != null) {
            currencyDao.insert(list)
            Log.e(TAG, "inserting currencies $list")
        } else {
            Log.e(TAG, "inserting currencies failed")
        }
    }

    suspend fun updateCurrencyFromRemote() {
        withContext(ioDispatcher) {
            var currencyRates: List<CurrencyRates>? = null
            try {
                currencyRates = listOf(
                    remote.getExchangeRate("USD"),
                    remote.getExchangeRate("EUR"),
                    remote.getExchangeRate("RUB"),
                    remote.getExchangeRate("UAH")
                )
            } catch (e: Exception) {
                Log.e(TAG, "failed update Currencies")
            }
            insertCurrencies(currencyRates)
        }
    }


    //Account

    suspend fun addAccount(account: Account) {
        withContext(ioDispatcher) {
            try {
                accountDao.insert(account)
            } catch (e: Exception) {
                Log.e("TAG", "failed addAccount : ${e.message}")
            }
        }
    }

    suspend fun getAccounts(): List<Account>? =
        withContext(ioDispatcher) {
            var account: List<Account>? = null
            try {
                account = accountDao.getListOfAccounts()
                Log.e(TAG, "failed getAccounts: $account ")
            } catch (e: Exception) {
                Log.e(TAG, "failed getAccounts: ${e.message} ")
            }
            return@withContext account
        }
    //Category

    //Sms
    suspend fun getSmsTransactions(): List<TransactionFromSms>? =
        withContext(ioDispatcher) {
            var transactionFromSms: List<TransactionFromSms>? = null
            try {
                transactionFromSms = smsDao.getListOfTransactionFromSms()
            } catch (e: Exception) {
                Log.e(TAG, "failed getTransactionFromSms: ${e.message} ")
            }
            return@withContext transactionFromSms
        }

    suspend fun addSmsTransaction(transactionFromSms: TransactionFromSms) {
        withContext(ioDispatcher) {
            try {
                smsDao.insert(transactionFromSms)
            } catch (e: Exception) {
                Log.e("TAG", "failed addTransactionFromSms : ${e.message}")
            }
        }
    }


    companion object {
        private const val TAG = "TransactionRepository"
    }
}
