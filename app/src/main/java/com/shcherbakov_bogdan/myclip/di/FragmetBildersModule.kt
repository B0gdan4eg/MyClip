package com.shcherbakov_bogdan.myclip.di

import com.shcherbakov_bogdan.myclip.ui.fragments.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): HomeFragment
}