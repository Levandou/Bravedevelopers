package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(private val repository: Repository) {

    fun addToFavorites(informationAboutPokemon: InformationAboutPokemon){
        repository.addToFavorites(informationAboutPokemon)
    }
}