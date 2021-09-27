package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon

class DeleteFromFavoritesUseCase(private val repository: Repository) {

    fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon){
        repository.deleteFromFavorites(informationAboutPokemon)
    }
}