package com.shcherbakov_bogdan.myclip.di

import com.shcherbakov_bogdan.myclip.ui.dialogs.DialogExpenses
import com.shcherbakov_bogdan.myclip.ui.dialogs.account.DialogAccount
import com.shcherbakov_bogdan.myclip.ui.fragments.currency.CurrencyFragment
import com.shcherbakov_bogdan.myclip.ui.fragments.home.HomeFragment
import com.shcherbakov_bogdan.myclip.ui.fragments.inbox.InboxFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCurrencyFragment(): CurrencyFragment

    @ContributesAndroidInjector
    abstract fun contributeInboxFragment(): InboxFragment

    @ContributesAndroidInjector
    abstract fun contributeDialogAccount(): DialogAccount

    @ContributesAndroidInjector
    abstract fun contributeDialogExpenses(): DialogExpenses
}