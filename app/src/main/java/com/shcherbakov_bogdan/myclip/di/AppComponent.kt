package com.shcherbakov_bogdan.myclip.di

import android.app.Application
import com.shcherbakov_bogdan.myclip.MyClip
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [AndroidInjectionModule::class, AppModule::class, ViewModelModule::class, MainActivityModule::class])
@Singleton
interface AppComponent : AndroidInjector<MyClip> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(myClip: MyClip)
}