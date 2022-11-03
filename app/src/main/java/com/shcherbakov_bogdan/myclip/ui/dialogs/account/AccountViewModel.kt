package com.shcherbakov_bogdan.myclip.ui.dialogs.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcherbakov_bogdan.myclip.data.account.Account
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    init {
        getAccounts()
    }

    private fun getAccounts() {
        viewModelScope.launch {
            val listResult = repository.getAccounts()
            if (listResult != null) {
                setAccounts(listResult)
            }
        }
    }

    private fun setAccounts(account: List<Account>) {
        _accounts.value = account
    }
}