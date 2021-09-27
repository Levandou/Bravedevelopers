package com.example.bravedevelopers.di

import android.app.Application
import android.content.Context
import com.example.bravedevelopers.api.ApiService
import com.example.bravedevelopers.data.AppDatabase
import com.example.bravedevelopers.data.PokemonsDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class Module(val application: Application)

{
    val baseURL = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }



    @Singleton
    @Provides
    fun getDao(appDatabase: AppDatabase): PokemonsDao {
        return appDatabase.resultDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): AppDatabase {
        return AppDatabase.getInstance(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }

}
