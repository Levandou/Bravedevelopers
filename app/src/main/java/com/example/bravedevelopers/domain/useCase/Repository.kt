package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon

interface Repository {

    fun addToFavorites(informationAboutPokemon: InformationAboutPokemon)

    fun randomSearch(informationAboutPokemon: InformationAboutPokemon)

    fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon)
}