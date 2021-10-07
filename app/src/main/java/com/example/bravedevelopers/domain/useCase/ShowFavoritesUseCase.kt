package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon
import javax.inject.Inject

class ShowFavoritesUseCase @Inject constructor(private val repository: Repository) {

    fun showFavorites():List<InformationAboutPokemon>{
        return repository.showFavorites()
    }
}
