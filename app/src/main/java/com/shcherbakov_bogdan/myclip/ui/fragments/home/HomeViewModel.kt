package com.shcherbakov_bogdan.myclip.ui.fragments.home

import android.app.Application
import androidx.lifecycle.*
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import kotlinx.coroutines.launch

class HomeViewModel(
    private val database: TransactionDao,
    application: Application
) : AndroidViewModel(application) {

}