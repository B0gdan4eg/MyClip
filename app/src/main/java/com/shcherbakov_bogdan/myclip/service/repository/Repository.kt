package com.shcherbakov_bogdan.myclip.service.repository

import com.shcherbakov_bogdan.myclip.data.CurrencyRates
import com.shcherbakov_bogdan.myclip.service.repository.remote.Remote
import io.reactivex.Observable

class Repository(
    private val remote: Remote
    ) {

    fun rates(periodicity: Int): Observable<List<CurrencyRates>> {
        return remote.rates(periodicity)
    }
}
