package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon
import io.reactivex.Single


interface Repository {

    fun addToFavorites(informationAboutPokemon: InformationAboutPokemon)

    fun randomSearch(informationAboutPokemon: InformationAboutPokemon)

    fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon)

    fun showFavorites():List<InformationAboutPokemon>

    fun getPokemonsList(url: String?): Single<InformationAboutPokemon>
}