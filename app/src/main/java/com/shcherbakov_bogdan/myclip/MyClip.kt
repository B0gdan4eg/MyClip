package com.shcherbakov_bogdan.myclip

import android.app.Application
import android.content.Context
import com.shcherbakov_bogdan.myclip.di.AppComponent
import com.shcherbakov_bogdan.myclip.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MyClip : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    lateinit var daggerComponent: DaggerAppComponent
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        var daggerComponent = DaggerAppComponent.builder().application(this)
            .build().inject(this)
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }
    override fun androidInjector() = dispatchingAndroidInjector

    fun Context.getAppComponent(): DaggerAppComponent {
        return when (this) {
            is MyClip -> daggerComponent
            else -> (this.applicationContext as MyClip).daggerComponent
        }
    }
}