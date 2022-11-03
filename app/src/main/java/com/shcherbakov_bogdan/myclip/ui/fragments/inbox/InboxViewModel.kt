package com.shcherbakov_bogdan.myclip.ui.fragments.inbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcherbakov_bogdan.myclip.data.sms.TransactionFromSms
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class InboxViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _smsTransactions = MutableLiveData<List<TransactionFromSms>>()
    val smsTransactions: LiveData<List<TransactionFromSms>> = _smsTransactions

    init {
        getSmsTransactions()
    }


    private fun getSmsTransactions() {
        viewModelScope.launch {
            val listResult = repository.getSmsTransactions()
            if (listResult != null) {
                setTransactions(listResult)
            }
        }
    }

    private fun setTransactions(transactionFromSms: List<TransactionFromSms>) {
        _smsTransactions.value = transactionFromSms
    }

}