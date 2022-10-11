package com.shcherbakov_bogdan.myclip.ui.fragments.home

import android.app.Application
import androidx.lifecycle.*
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import javax.inject.Inject

class HomeViewModel
@Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _transactions = MutableLiveData<List<Transactions>>()
    val transactions: LiveData<List<Transactions>> = _transactions

    fun add() {

    }
    fun refresh() {

    }
    fun delete() {

    }

    fun getTransactionsList() = repository.getTransactionsList()
}