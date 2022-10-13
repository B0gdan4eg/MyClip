package com.shcherbakov_bogdan.myclip

import android.app.Application
import com.shcherbakov_bogdan.myclip.di.AppComponent
import com.shcherbakov_bogdan.myclip.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins

class MyClip : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }
}