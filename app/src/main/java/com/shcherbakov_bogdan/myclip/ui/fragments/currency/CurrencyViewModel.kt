package com.shcherbakov_bogdan.myclip.ui.fragments.currency

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import com.shcherbakov_bogdan.myclip.utils.Const
import com.shcherbakov_bogdan.myclip.utils.Rates
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getCurrencies()
    }

    private var usdRate = Const.BASE_RATE
    private var eurRate = Const.BASE_RATE
    private var rubRate = Const.BASE_RATE
    private var uahRate = Const.BASE_RATE

    private var _bynAmount = MutableLiveData(1.0)
    val bynAmount: LiveData<Double> = _bynAmount

    private var _usdAmount = MutableLiveData(usdRate)
    val usdAmount: LiveData<Double> = _usdAmount

    private var _eurAmount = MutableLiveData(eurRate)
    val eurAmount: LiveData<Double> = _eurAmount

    private var _rubAmount = MutableLiveData(rubRate)
    val rubAmount: LiveData<Double> = _rubAmount

    private var _uahAmount = MutableLiveData(uahRate)
    val uahAmount: LiveData<Double> = _uahAmount


    private fun updateAmount(rates: Rates) {
        when (rates) {
            Rates.USD -> _usdAmount.value = _bynAmount.value?.times(usdRate)
            Rates.EUR -> _eurAmount.value = _bynAmount.value?.times(eurRate)
            Rates.RUB -> _rubAmount.value = _bynAmount.value?.times(rubRate)
            Rates.UAH -> _uahAmount.value = _bynAmount.value?.times(uahRate)
            else -> Log.e(TAG, "failed get updateAmount")
        }
    }

    fun updateOtherFields(amount: Double, rates: Rates) {
        when (rates) {
            Rates.USD -> {
                _bynAmount.value = amount / usdRate
                updateAmount(Rates.EUR)
                updateAmount(Rates.RUB)
                updateAmount(Rates.UAH)
            }
            Rates.EUR -> {
                _bynAmount.value = amount / eurRate
                updateAmount(Rates.USD)
                updateAmount(Rates.RUB)
                updateAmount(Rates.UAH)
            }
            Rates.RUB -> {
                _bynAmount.value = amount / rubRate
                updateAmount(Rates.USD)
                updateAmount(Rates.EUR)
                updateAmount(Rates.UAH)
            }
            Rates.UAH -> {
                _bynAmount.value = amount / uahRate
                updateAmount(Rates.USD)
                updateAmount(Rates.EUR)
                updateAmount(Rates.RUB)
            }
            Rates.BYN -> {
                _usdAmount.value = amount.times(usdRate)
                _eurAmount.value = amount.times(eurRate)
                _rubAmount.value = amount.times(rubRate)
                _uahAmount.value = amount.times(uahRate)
            }
            else -> Log.e(TAG, "failed get updateFields")
        }
    }
    companion object {
        private const val TAG = "CurrencyViewModel"
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            repository.updateCurrencyFromRemote()
            val listResult = repository.getCurrencies()
            if (listResult != null){
                setCurrencies(listResult)
            }
        }
    }

    private fun setCurrencies(currencyRates: List<CurrencyRates>){
        for (currency in currencyRates) {
            when (currency.abbreviation) {
                Rates.USD.name -> usdRate = currency.rate
                Rates.EUR.name -> eurRate = currency.rate
                Rates.RUB.name -> rubRate = 100 / currency.rate
                Rates.UAH.name -> uahRate = 100 / currency.rate
            }
        }
    }
}