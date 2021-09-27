package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon

class RandomSearchUseCase(private val repository: Repository) {

    fun randomSearch(informationAboutPokemon: InformationAboutPokemon){
        repository.randomSearch(informationAboutPokemon)
    }
}