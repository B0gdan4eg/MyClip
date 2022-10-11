package com.shcherbakov_bogdan.myclip

import android.app.Application
import com.shcherbakov_bogdan.myclip.di.AppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import java.util.stream.DoubleStream.builder
import javax.inject.Inject

class MyClip : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
