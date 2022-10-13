package com.shcherbakov_bogdan.myclip.di

import android.app.Application
import com.shcherbakov_bogdan.myclip.MyClip
import com.shcherbakov_bogdan.myclip.ui.fragments.home.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent {
    fun viewModelsFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myClip: MyClip)
}