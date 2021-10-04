package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(private val repository: Repository) {

    fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon){
        repository.deleteFromFavorites(informationAboutPokemon)
    }
}