package com.example.bravedevelopers.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bravedevelopers.domain.InformationAboutPokemon


@Database(entities = [InformationAboutPokemon::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    companion object{
        const val DB_NAME="main.db2"
    }
    abstract fun resultDao():PokemonsDao
}