package com.example.bravedevelopers.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bravedevelopers.domain.InformationAboutPokemon


@Dao
interface PokemonsDao {
@Query("SELECT * FROM name_list ORDER BY name ")
    fun getNameList():LiveData< List <InformationAboutPokemon> >
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNames(resultList: InformationAboutPokemon)
}