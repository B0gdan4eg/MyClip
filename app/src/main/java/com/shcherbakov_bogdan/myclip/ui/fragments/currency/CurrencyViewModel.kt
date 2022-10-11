package com.shcherbakov_bogdan.myclip.ui.fragments.currency

import androidx.lifecycle.ViewModel
import com.shcherbakov_bogdan.myclip.service.repository.Repository
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun rates() = repository.rates(1)
}