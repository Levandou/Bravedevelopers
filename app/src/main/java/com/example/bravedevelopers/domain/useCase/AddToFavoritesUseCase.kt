package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon

class AddToFavoritesUseCase(private val repository: Repository) {

    fun addToFavorites(informationAboutPokemon: InformationAboutPokemon){
        repository.addToFavorites(informationAboutPokemon)
    }
}