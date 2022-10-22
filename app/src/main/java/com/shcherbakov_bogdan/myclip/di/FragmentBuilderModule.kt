package com.shcherbakov_bogdan.myclip.di

import com.shcherbakov_bogdan.myclip.ui.fragments.currency.CurrencyFragment
import com.shcherbakov_bogdan.myclip.ui.fragments.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyFragment(): CurrencyFragment
}