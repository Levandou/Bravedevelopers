package com.example.bravedevelopers.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bravedevelopers.domain.InformationAboutPokemon


@Dao
interface PokemonsDao {

@Query("SELECT * FROM name_list2 ORDER BY name ")
    fun getNameList(): List <InformationAboutPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(informationAboutPokemon: InformationAboutPokemon)
    @Delete
    fun deleteFavorite(informationAboutPokemon: InformationAboutPokemon)

}