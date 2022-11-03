package com.shcherbakov_bogdan.myclip

import android.util.Log
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyRates
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.utils.Const
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.CountDownLatch

class ApiTest {
    lateinit var remote: Remote

    @Before
    @Throws(Exception::class)
    fun createDb() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        remote =
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(Const.BASE_URL)
                .build().create(Remote::class.java)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun getNewBooks_Success() {
        val latch = CountDownLatch(1)
        var response: CurrencyRates? = null
        GlobalScope.launch {
            try {
                response = remote.getExchangeRate("USD")
                latch.countDown()
            } catch (e: Exception) {
                Log.e("ApiTest", "failed getNewBooks")
            }
        }
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        println(response.toString())
        assert(response?.id == 431)
        assert(response != null)
        assertTrue(response?.abbreviation == "USD")
    }
}