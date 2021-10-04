package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon
import javax.inject.Inject

class RandomSearchUseCase @Inject constructor(private val repository: Repository) {

    fun randomSearch(informationAboutPokemon: InformationAboutPokemon){
        repository.randomSearch(informationAboutPokemon)
    }
}