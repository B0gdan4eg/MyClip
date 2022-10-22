package com.shcherbakov_bogdan.myclip.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface Remote {

    @GET("rates/{abbreviation}?parammode=2")
    suspend fun getExchangeRate(@Path("abbreviation") abbreviation: String): CurrencyRates

}