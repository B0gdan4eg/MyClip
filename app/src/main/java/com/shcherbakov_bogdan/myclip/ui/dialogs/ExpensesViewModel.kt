package com.shcherbakov_bogdan.myclip.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExpensesViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {


    fun saveTransaction(dayOfWeek: String, amount: Double, date: String, description: String) {
        viewModelScope.launch {
            repository.addTransaction(
                Transactions(
                    dayOfWeek,
                    amount,
                    date,
                    false,
                    1,
                    1,
                    description
                )
            )
        }
    }
}