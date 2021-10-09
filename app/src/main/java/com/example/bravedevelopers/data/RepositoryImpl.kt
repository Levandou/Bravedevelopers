package com.example.bravedevelopers.data

import com.example.bravedevelopers.api.ApiService

import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.Repository
import io.reactivex.Single

class RepositoryImpl (private val pokemonsDao: PokemonsDao,private val apiService: ApiService):Repository {

    override fun addToFavorites(informationAboutPokemon: InformationAboutPokemon) {
        pokemonsDao.insertFavorite(informationAboutPokemon)
    }

    override fun randomSearch(informationAboutPokemon: InformationAboutPokemon) {


    }

    override fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon) {
    pokemonsDao.deleteFavorite(informationAboutPokemon)


    }

    override fun showFavorites(): List<InformationAboutPokemon> {
       return pokemonsDao.getNameList()
    }



    override fun getPokemonsList(url: String?): Single<InformationAboutPokemon> {
        return apiService.getInformationAboutPokemon(url)
    }
}