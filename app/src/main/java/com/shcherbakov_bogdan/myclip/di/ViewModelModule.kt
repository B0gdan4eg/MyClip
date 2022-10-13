package com.shcherbakov_bogdan.myclip.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shcherbakov_bogdan.myclip.ui.fragments.home.HomeViewModel
import com.shcherbakov_bogdan.myclip.ui.fragments.home.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindSpecialtyViewModel(viewModel: HomeViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
