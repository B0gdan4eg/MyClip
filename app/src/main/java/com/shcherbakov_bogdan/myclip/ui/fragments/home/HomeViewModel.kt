package com.shcherbakov_bogdan.myclip.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcherbakov_bogdan.myclip.MyClip
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel
@Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    fun getTransactions(): LiveData<List<Transactions>> {
        return repository.getListOfTransactions()
    }

}