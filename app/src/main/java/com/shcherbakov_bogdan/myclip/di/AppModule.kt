package com.shcherbakov_bogdan.myclip.di

import android.app.Application
import androidx.room.Room
import com.shcherbakov_bogdan.myclip.data.DB_NAME
import com.shcherbakov_bogdan.myclip.data.account.AccountDao
import com.shcherbakov_bogdan.myclip.data.category.CategoryDao
import com.shcherbakov_bogdan.myclip.data.transactions.TransactionDao
import com.shcherbakov_bogdan.myclip.data.Database
import com.shcherbakov_bogdan.myclip.data.remote.Remote
import com.shcherbakov_bogdan.myclip.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideRemote(): Remote {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(Remote::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): Database {
        return Room
            .databaseBuilder(app, Database::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTransactionDao(db: Database): TransactionDao {
        return db.transactionDao()
    }

    @Singleton
    @Provides
    fun provideAccountDao(db: Database): AccountDao {
        return db.accountDao()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: Database): CategoryDao {
        return db.categoryDao()
    }
}