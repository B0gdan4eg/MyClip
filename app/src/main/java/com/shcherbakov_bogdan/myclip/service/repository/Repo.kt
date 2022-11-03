package com.shcherbakov_bogdan.myclip.service.repository

import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions

interface Repo {
    //fun getTasksStream(): Flow<Result<List<Task>>>

    suspend fun getTransactions(): List<Transactions>?

    //fun getTaskStream(taskId: String): Flow<Result<Task>>

    suspend fun addTransaction(transactions: Transactions)

    //suspend fun deleteAllTransactions()


    suspend fun getCurrencies(): List<CurrencyRates>?
}