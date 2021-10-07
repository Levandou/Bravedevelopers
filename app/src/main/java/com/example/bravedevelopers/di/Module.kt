package com.example.bravedevelopers.di


import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bravedevelopers.api.ApiService
import com.example.bravedevelopers.data.AppDatabase
import com.example.bravedevelopers.data.PokemonsDao
import com.example.bravedevelopers.data.RepositoryImpl
import com.example.bravedevelopers.domain.useCase.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class Module(/*val application: Application*/)

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
    fun getRoomDbInstance(application: Application): AppDatabase {


        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )/*.addMigrations(AppDatabase.MIGRATION)*/.allowMainThreadQueries().build()
    }



    @Singleton
    @Provides
    fun provideRepository(pokemonsDao: PokemonsDao, apiService: ApiService):Repository{
        return RepositoryImpl(pokemonsDao, apiService)
    }


}
