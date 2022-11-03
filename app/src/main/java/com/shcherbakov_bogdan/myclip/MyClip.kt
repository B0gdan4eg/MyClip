package com.shcherbakov_bogdan.myclip

import android.app.Application
import com.shcherbakov_bogdan.myclip.data.account.Account
import com.shcherbakov_bogdan.myclip.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MyClip : Application(), HasAndroidInjector {

    val account: Account = Account(0L, "Main", 0.00)

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }

    override fun androidInjector() = dispatchingAndroidInjector

}