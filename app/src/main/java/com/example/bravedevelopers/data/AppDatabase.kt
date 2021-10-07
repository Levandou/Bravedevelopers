package com.example.bravedevelopers.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bravedevelopers.domain.InformationAboutPokemon


@Database(entities = [InformationAboutPokemon::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    companion object{
        const val DB_NAME="main.db2"
     /*   private var db:AppDatabase?=null
        private val LOCK=Any()
        //
        fun  getInstance(context: Context):AppDatabase{
            synchronized(LOCK) {
                db?.let { return it }
                    val instance =
                        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                    db = instance
                return instance
            }
        }
*/
    /* val MIGRATION: Migration = object : Migration(1, 2) {
         override fun migrate(database: SupportSQLiteDatabase) {
             database.execSQL("ALTER TABLE InformationAboutPokemon ADD COLUMN isFavorite INTEGER DEFAULT 0 NOT NULL")
         }
     }*/
    }
    abstract fun resultDao():PokemonsDao
}