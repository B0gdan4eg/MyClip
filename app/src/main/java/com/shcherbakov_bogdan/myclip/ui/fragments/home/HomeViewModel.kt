package com.shcherbakov_bogdan.myclip.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import com.shcherbakov_bogdan.myclip.service.repository.TransactionListener
import javax.inject.Inject

class HomeViewModel
@Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _transactions = MutableLiveData<Result<List<Transactions>>>()
    val transactions: LiveData<Result<List<Transactions>>> = _transactions

    fun getTransactions() = repository.loadUsers()
}