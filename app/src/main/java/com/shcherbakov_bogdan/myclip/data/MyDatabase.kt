package com.shcherbakov_bogdan.myclip.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shcherbakov_bogdan.myclip.data.account.Account
import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.Category
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.sms.SmsDao
import com.shcherbakov_bogdan.myclip.data.sms.TransactionFromSms
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions

const val DB_NAME = "finance.db"


@Database(
    entities = [
        Transactions::class,
        Account::class,
        Category::class,
        TransactionFromSms::class,
        CurrencyRates::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun smsDao(): SmsDao
    abstract fun currencyDao(): CurrencyDao

}