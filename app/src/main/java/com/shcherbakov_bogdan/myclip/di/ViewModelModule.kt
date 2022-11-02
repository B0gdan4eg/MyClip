package com.shcherbakov_bogdan.myclip.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shcherbakov_bogdan.myclip.ui.dialogs.account.AccountViewModel
import com.shcherbakov_bogdan.myclip.ui.fragments.currency.CurrencyViewModel
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
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
