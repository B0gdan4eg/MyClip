package com.shcherbakov_bogdan.myclip.service.repository.remote

import com.shcherbakov_bogdan.myclip.data.CurrencyRates
import com.shcherbakov_bogdan.myclip.utils.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Remote {

    @GET("rates")
    fun rates(@Query("periodicity") periodicity: Int): Observable<List<CurrencyRates>>

    companion object Factory {
        fun create(): Remote {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(Remote::class.java)
        }
    }
}