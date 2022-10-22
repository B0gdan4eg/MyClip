package com.shcherbakov_bogdan.myclip.di

import android.app.Application
import androidx.room.Room
import com.shcherbakov_bogdan.myclip.data.DB_NAME
import com.shcherbakov_bogdan.myclip.data.MyDatabase
import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.remote.CurrencyDao
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.data.sms.SmsDao
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.utils.Const
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRemote(moshi: Moshi): Remote {

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Const.BASE_URL)
            .build()
            .create(Remote::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): MyDatabase {
        return Room
            .databaseBuilder(app, MyDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .createFromAsset("finance.db").build()
    }

    @Singleton
    @Provides
    fun provideTransactionDao(db: MyDatabase): TransactionDao {
        return db.transactionDao()
    }

    @Singleton
    @Provides
    fun provideAccountDao(db: MyDatabase): AccountDao {
        return db.accountDao()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: MyDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Singleton
    @Provides
    fun provideSmsDao(db: MyDatabase): SmsDao {
        return db.smsDao()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(db: MyDatabase): CurrencyDao {
        return db.currencyDao()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {

        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

}